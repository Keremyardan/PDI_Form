import React, { useEffect, useState } from "react";
import "./UserManagement.css";

const UserManagement = () => {
  const [officers, setOfficers] = useState([]);
  const [admins, setAdmins] = useState([]);
  const [newAdmin, setNewAdmin] = useState({ email: "", password: "" });
  const [newOfficer, setNewOfficer] = useState({ email: "", password: "" });

  const [adminMessage, setAdminMessage] = useState("");
  const [officerMessage, setOfficerMessage] = useState("");

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
    setAdminMessage("");
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
        setAdminMessage("✅ Admin başarıyla oluşturuldu.");
        setNewAdmin({ email: "", password: "" });
        fetchAdmins();
      } else {
        setAdminMessage("❌ Admin oluşturulamadı: " + (data.message || "Hata"));
      }
    } catch (error) {
      setAdminMessage("❌ Sunucu hatası: " + error.message);
    }
  };

  const createOfficer = async () => {
    setOfficerMessage("");
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
        setOfficerMessage("✅ Officer başarıyla oluşturuldu.");
        setNewOfficer({ email: "", password: "" });
        fetchOfficers();
      } else {
        setOfficerMessage("❌ Officer oluşturulamadı: " + (data.message || "Hata"));
      }
    } catch (error) {
      setOfficerMessage("❌ Sunucu hatası: " + error.message);
    }
  };

  const deleteAdmin = async () => {
    if (!selectedAdminId) return;
    await fetch(`http://localhost:8080/administrators/delete-admin/${selectedAdminId}`, {
      method: "DELETE",
      headers: { Authorization: `Basic ${auth}` }
    });
    setSelectedAdminId("");
    fetchAdmins();
  };

  const deleteOfficer = async () => {
    if (!selectedOfficerId) return;
    await fetch(`http://localhost:8080/administrators/delete-officer/${selectedOfficerId}`, {
      method: "DELETE",
      headers: { Authorization: `Basic ${auth}` }
    });
    setSelectedOfficerId("");
    fetchOfficers();
  };

  useEffect(() => {
    fetchAdmins();
    fetchOfficers();
  }, []);

  return (
  <div className="user-management">
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
        <button className="form-button" onClick={createAdmin}>Admin Ekle</button>
        {adminMessage && <p className="message">{adminMessage}</p>}

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
        <button className="form-button" onClick={createOfficer}>Officer Ekle</button>
        {officerMessage && <p className="message">{officerMessage}</p>}
      </div>

      <div className="delete-section">
        <h2>Admin Kullanıcıyı Sil</h2>
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

        <h2>Officer Kullanıcıyı Sil</h2>
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
      </div>
    </div>
  </div>
);

};

export default UserManagement;
