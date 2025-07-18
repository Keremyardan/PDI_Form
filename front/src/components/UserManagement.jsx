import React, { useEffect, useState } from "react";
import "./UserManagement.css";
import GoBackButton from "./GoBackButton"

const UserManagement = () => {
  const [officers, setOfficers] = useState([]);
  const [admins, setAdmins] = useState([]);
  const [newAdmin, setNewAdmin] = useState({ email: "", password: "" });
  const [newOfficer, setNewOfficer] = useState({ email: "", password: "" });

  const [createAdminMessage, setCreateAdminMessage] = useState("");
  const [createOfficerMessage, setCreateOfficerMessage] = useState("");
  const [deleteAdminMessage, setDeleteAdminMessage] = useState("");
  const [deleteOfficerMessage, setDeleteOfficerMessage] = useState("");

  const [selectedAdminId, setSelectedAdminId] = useState("");
  const [selectedOfficerId, setSelectedOfficerId] = useState("");

  const auth = localStorage.getItem("auth");
  const role = localStorage.getItem("role");

  if (role !== "ADMIN") return <div className="access-denied">Erişim reddedildi.</div>;

  const fetchOfficers = async () => {
    const res = await fetch("http://localhost:8080/administrators/all-officers", {
      headers: { Authorization: `Basic ${auth}` }
    });
    const data = await res.json();
    setOfficers(data.data || []);
  };

  const fetchAdmins = async () => {
    const res = await fetch("http://localhost:8080/administrators/all-admins", {
      headers: { Authorization: `Basic ${auth}` }
    });
    const data = await res.json();
    setAdmins(data.data || []);
  };

  const createAdmin = async () => {
    setCreateAdminMessage("");
    setCreateOfficerMessage("");
    setDeleteAdminMessage("");
    setDeleteOfficerMessage("");

    try {
      const res = await fetch("http://localhost:8080/administrators/save", {
        method: "POST",
        headers: {
          Authorization: `Basic ${auth}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify(newAdmin)
      });

      const data = await res.json();
      if (res.ok && data.success) {
        setCreateAdminMessage("✅ Admin başarıyla oluşturuldu.");
        setNewAdmin({ email: "", password: "" });
        fetchAdmins();
      } else {
        setCreateAdminMessage("❌ Böyle bir kullanıcı mevcut: ");
      }
    } catch (error) {
      setCreateAdminMessage("❌ Sunucu hatası: " + error.message);
    }
  };

  const createOfficer = async () => {
    setCreateOfficerMessage("");
    setCreateAdminMessage("");
    setDeleteAdminMessage("");
    setDeleteOfficerMessage("");

    try {
      const res = await fetch("http://localhost:8080/administrators/create-officer", {
        method: "POST",
        headers: {
          Authorization: `Basic ${auth}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify(newOfficer)
      });

      const data = await res.json();
      if (res.ok && data.success) {
        setCreateOfficerMessage("✅ Officer başarıyla oluşturuldu.");
        setNewOfficer({ email: "", password: "" });
        fetchOfficers();
      } else {
        setCreateOfficerMessage("❌ Böyle bir kullanıcı mevcut: ");
      }
    } catch (error) {
      setCreateOfficerMessage("❌ Sunucu hatası: " + error.message);
    }
  };

  const deleteAdmin = async () => {
    setDeleteAdminMessage("");
    setCreateAdminMessage("");
    setCreateOfficerMessage("");
    setDeleteOfficerMessage("");

    if (!selectedAdminId) return;

    try {
      const res = await fetch(`http://localhost:8080/administrators/delete-admin/${selectedAdminId}`, {
        method: "DELETE",
        headers: { Authorization: `Basic ${auth}` }
      });

      const data = await res.json();
      if (res.ok && data.success) {
        setDeleteAdminMessage("✅ Admin başarıyla silindi.");
        setSelectedAdminId("");
        fetchAdmins();
      } else {
        setDeleteAdminMessage("❌ Admin silinemedi: ");
      }
    } catch (error) {
      setDeleteAdminMessage("❌ Sunucu hatası: " + error.message);
    }
  };

  const deleteOfficer = async () => {
    setDeleteOfficerMessage("");
    setCreateAdminMessage("");
    setCreateOfficerMessage("");
    setDeleteAdminMessage("");

    if (!selectedOfficerId) return;

    try {
      const res = await fetch(`http://localhost:8080/administrators/delete-officer/${selectedOfficerId}`, {
        method: "DELETE",
        headers: { Authorization: `Basic ${auth}` }
      });

      const data = await res.json();
      if (res.ok && data.success) {
        setDeleteOfficerMessage("✅ Officer başarıyla silindi.");
        setSelectedOfficerId("");
        fetchOfficers();
      } else {
        setDeleteOfficerMessage("❌ Officer silinemedi: ");
      }
    } catch (error) {
      setDeleteOfficerMessage("❌ Sunucu hatası: " + error.message);
    }
  };

  useEffect(() => {
    fetchAdmins();
    fetchOfficers();
  }, []);

  return (
    <div className="user-management">
      <GoBackButton />
     <div className="form-section">
  <div className="create-section">
    <h2>Yeni Admin Oluştur</h2>
    <input
      className="form-input"
      type="email"
      placeholder="Email"
      value={newAdmin.email}
      onChange={(e) => setNewAdmin({ ...newAdmin, email: e.target.value })}
    />
    <input
      className="form-input"
      type="password"
      placeholder="Şifre"
      value={newAdmin.password}
      onChange={(e) => setNewAdmin({ ...newAdmin, password: e.target.value })}
    />
    <button className="form-button1" onClick={createAdmin}>Kaydet</button>
    {/* admin oluşturma mesajı en altta */}
    {createAdminMessage && <p className="message">{createAdminMessage}</p>}

    <h2>Yeni Officer Oluştur</h2>
    <input
      className="form-input"
      type="email"
      placeholder="Email"
      value={newOfficer.email}
      onChange={(e) => setNewOfficer({ ...newOfficer, email: e.target.value })}
    />
    <input
      className="form-input"
      type="password"
      placeholder="Şifre"
      value={newOfficer.password}
      onChange={(e) => setNewOfficer({ ...newOfficer, password: e.target.value })}
    />
    <button className="form-button1" onClick={createOfficer}>Kaydet</button>
    {/* officer oluşturma mesajı en altta */}
    {createOfficerMessage && <p className="message">{createOfficerMessage}</p>}
  </div>

  <div className="delete-section">
    <h2>Admin Sil</h2>
    <select
      className="select-box"
      value={selectedAdminId}
      onChange={(e) => setSelectedAdminId(e.target.value)}
    >
      <option value="">Admin seç</option>
      {admins.map((admin) => (
        <option key={admin.id} value={admin.id}>
          {admin.email}
        </option>
      ))}
    </select>
    <button className="form-button" onClick={deleteAdmin} disabled={!selectedAdminId}>Sil</button>
    {/* admin silme mesajı en altta */}
    {deleteAdminMessage && <p className="message">{deleteAdminMessage}</p>}

    <h2>Officer Sil</h2>
    <select
      className="select-box"
      value={selectedOfficerId}
      onChange={(e) => setSelectedOfficerId(e.target.value)}
    >
      <option value="">Officer seç</option>
      {officers.map((officer) => (
        <option key={officer.id} value={officer.id}>
          {officer.email}
        </option>
      ))}
    </select>
    <button className="form-button" onClick={deleteOfficer} disabled={!selectedOfficerId}>Sil</button>
    {/* officer silme mesajı en altta */}
    {deleteOfficerMessage && <p className="message">{deleteOfficerMessage}</p>}
  </div>
</div>

    </div>
  );
};

export default UserManagement;
