import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import PdiForm from '../components/PdiForm';

const FormDetail = () => {
  const { id } = useParams();
  const [form, setForm] = useState(null);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchForm = async () => {
      const credentials = localStorage.getItem("auth");
      if (!credentials) {
        setError("Giriş yapılmamış.");
        return;
      }

      try {
        const response = await fetch(`http://localhost:8080/api/pdi-form/${id}`, {
          headers: {
            Authorization: `Basic ${credentials}`
          }
        });

        if (!response.ok) throw new Error("Form alınamadı");
        const data = await response.json();
        setForm(data);
      } catch (err) {
        console.error(err);
        setError("Form yüklenemedi.");
      }
    };

    fetchForm();
  }, [id]);

  if (error) return <p>{error}</p>;
  if (!form) return <p>Yükleniyor...</p>;

  return <PdiForm form={form} isReadOnly={false} />;
};
export default FormDetail;
