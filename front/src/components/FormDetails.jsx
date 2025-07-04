import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import PdiForm from '../components/PdiForm';

const FormDetail = () => {
  const { id } = useParams();
  const [form, setForm] = useState(null);

  useEffect(() => {
    const fetchForm = async () => {
      try {
       const auth = localStorage.getItem("auth");
if (!auth) {
  console.warn("Giriş yapılmamış");
  return;
}

const res = await fetch(`http://localhost:8080/api/pdi-form/${id}`, {
  method: "GET",
  headers: {
    "Authorization": `Basic ${auth}`
  },
  credentials: "include"
});
        if (!res.ok) throw new Error("Veri alınamadı");
        const data = await res.json();
        setForm(data); // Eğer backend response'u {status: true, data: {...}} ise
      } catch (e) {
        console.error("Form çekilemedi:", e);
      }
    };

    fetchForm();
  }, [id]);

  if (!form) return <p>Yükleniyor...</p>;

  return <PdiForm isReadOnly={true} form={form} />;
};

export default FormDetail;
