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

  const [updateAdminMessage, setUpdateAdminMessage] = useState("");
  const [adminUpdateData, setAdminUpdateData] = useState({ id: "", name: "", officerId: "", password:"" });

  const [updateOfficerMessage, setUpdateOfficerMessage] = useState("");
  const [officerUpdateData, setOfficerUpdateData] = useState({ id: "", name: "", email: "", password: "" });
  <input
    className="form-input"
    type="password"
    placeholder="Yeni Şifre (boşsa değişmez)"
    value={officerUpdateData.password}
    onChange={(e) => setOfficerUpdateData({ ...officerUpdateData, password: e.target.value })}
  />




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

  const updateAdmin = async () => {
    setUpdateAdminMessage("");
    try {
      const res = await fetch("http://localhost:8080/administrators/update", {
        method: "PUT",
        headers: {
          Authorization: `Basic ${auth}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify(adminUpdateData)
      });

      const data = await res.json();
      if (res.ok && data.success) {
        setUpdateAdminMessage("✅ Admin başarıyla güncellendi.");
        setAdminUpdateData({ id: "", name: "", officerId: "" });
        fetchAdmins();
      } else {
        setUpdateAdminMessage("❌ Güncelleme başarısız: " + data.message);
      }
    } catch (error) {
      setUpdateAdminMessage("❌ Sunucu hatası: " + error.message);
    }
  };

  const updateOfficer = async () => {
    setUpdateOfficerMessage("");
    try {
      const res = await fetch("http://localhost:8080/officers/update", {
        method: "PUT",
        headers: {
          Authorization: `Basic ${auth}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify(officerUpdateData)
      });


      const data = await res.json();
      if (res.ok && data.success) {
        setUpdateOfficerMessage("✅ Officer başarıyla güncellendi.");
        setOfficerUpdateData({ id: "", name: "" });
        fetchOfficers();
      } else {
        setUpdateOfficerMessage("❌ Güncelleme başarısız: " + data.message);
      }
    } catch (error) {
      setUpdateOfficerMessage("❌ Sunucu hatası: " + error.message);
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

      <div className="form-grid">
        <div className="form-box">
          <h2>Yeni Admin Oluştur</h2>
          <input className="form-input" type="email" placeholder="Email" value={newAdmin.email}
            onChange={(e) => setNewAdmin({ ...newAdmin, email: e.target.value })} />
          <input className="form-input" type="password" placeholder="Şifre" value={newAdmin.password}
            onChange={(e) => setNewAdmin({ ...newAdmin, password: e.target.value })} />
          <button className="button-primary" onClick={createAdmin}>Kaydet</button>
          {createAdminMessage && <p className="message">{createAdminMessage}</p>}

          <h2>Yeni Officer Oluştur</h2>
          <input className="form-input" type="email" placeholder="Email" value={newOfficer.email}
            onChange={(e) => setNewOfficer({ ...newOfficer, email: e.target.value })} />
          <input className="form-input" type="password" placeholder="Şifre" value={newOfficer.password}
            onChange={(e) => setNewOfficer({ ...newOfficer, password: e.target.value })} />
          <button className="button-primary" onClick={createOfficer}>Kaydet</button>
          {createOfficerMessage && <p className="message">{createOfficerMessage}</p>}
        </div>

        <div className="form-box">
          <h2>Admin Sil</h2>
          <select className="select-box" value={selectedAdminId}
            onChange={(e) => setSelectedAdminId(e.target.value)}>
            <option value="">Admin seç</option>
            {admins.map((admin) => (
              <option key={admin.id} value={admin.id}>{admin.email}</option>
            ))}
          </select>
          <button className="button-danger" onClick={deleteAdmin} disabled={!selectedAdminId}>Sil</button>
          {deleteAdminMessage && <p className="message">{deleteAdminMessage}</p>}

          <h2>Officer Sil</h2>
          <select className="select-box" value={selectedOfficerId}
            onChange={(e) => setSelectedOfficerId(e.target.value)}>
            <option value="">Officer seç</option>
            {officers.map((officer) => (
              <option key={officer.id} value={officer.id}>{officer.email}</option>
            ))}
          </select>
          <button className="button-danger" onClick={deleteOfficer} disabled={!selectedOfficerId}>Sil</button>
          {deleteOfficerMessage && <p className="message">{deleteOfficerMessage}</p>}
        </div>

        <div className="form-box">
          <h2>Admin Güncelle</h2>
          <select className="select-box" value={adminUpdateData.id}
            onChange={(e) => setAdminUpdateData({ ...adminUpdateData, id: e.target.value })}>
            <option value="">Admin seç</option>
            {admins.map((admin) => (
              <option key={admin.id} value={admin.id}>{admin.email}</option>
            ))}
          </select>
          <input className="form-input" type="text" placeholder="Yeni E-mail" value={adminUpdateData.name}
            onChange={(e) => setAdminUpdateData({ ...adminUpdateData, name: e.target.value })} />
            <input
  className="form-input"
  type="password"
  placeholder="Yeni Şifre (boşsa değişmez)"
  value={adminUpdateData.password}
  onChange={(e) => setAdminUpdateData({ ...adminUpdateData, password: e.target.value })}
/>

          <button className="button-primary" onClick={updateAdmin}>Güncelle</button>
          {updateAdminMessage && <p className="message">{updateAdminMessage}</p>}

          <h2>Officer Güncelle</h2>
          <select className="select-box" value={officerUpdateData.id}
            onChange={(e) => setOfficerUpdateData({ ...officerUpdateData, id: e.target.value })}>
            <option value="">Officer seç</option>
            {officers.map((officer) => (
              <option key={officer.id} value={officer.id}>{officer.email}</option>
            ))}
          </select>
          <input className="form-input" type="text" placeholder="Yeni E-mail" value={officerUpdateData.name}
            onChange={(e) => setOfficerUpdateData({ ...officerUpdateData, name: e.target.value })} />
            <input
  className="form-input"
  type="password"
  placeholder="Yeni Şifre (boşsa değişmez)"
  value={officerUpdateData.password}
  onChange={(e) => setOfficerUpdateData({ ...officerUpdateData, password: e.target.value })}
/>

          <button className="button-primary" onClick={updateOfficer}>Güncelle</button>
          {updateOfficerMessage && <p className="message">{updateOfficerMessage}</p>}
        </div>

      </div>
    </div>
  );

};

export default UserManagement;
