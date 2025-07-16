import React, { useEffect, useState } from 'react'
import "../components/FormView.css"
import { useNavigate } from 'react-router-dom';

function FormView() {

  const [forms, setForms] = useState([]);
  const [error, setError] = useState('')
  const navigate = useNavigate();

  useEffect(() => {
    const fetchForms = async () => {
      try {
        const encodedCredentials = localStorage.getItem("auth");

        if (!encodedCredentials) {
          setError("Giriş bilgisi bulunamadı. Lütfen tekrar giriş yapın.");
          return;
        }

    const response = await fetch('http://localhost:8080/api/pdi-form/all', {
  method: 'GET',
  headers: {
    'Authorization': `Basic ${encodedCredentials}`
  },
  credentials: 'include'
});


        if (!response.ok) {
          if (response.status === 401) {
            setError("Oturumunuz sona erdi. Lütfen tekrar giriş yapın.");
          } else if (response.status === 403) {
            setError("Yetkiniz yok.");
          } else {
            setError(`Sunucu hatası: ${response.status}`);
          }
          return;
        }

        const data = await response.json();
        console.log("Gelen veri:", data);
        setForms(data);
      } catch (err) {
        console.error("Fetch error:", err);
        setError("Veriler getirilirken hata oluştu.");
      }
    };

    fetchForms();
  }, []);


  const formatDateTime = (isoString) => {
    const date = new Date(isoString);
    return date.toLocaleDateString('tr-TR') + ' ' + date.toLocaleTimeString('tr-TR', { hour: '2-digit', minute: '2-digit' });
  };


  return (
    <div className='form-view-container'>
      <h2>PDI Formlarım</h2>

      {error && <p className='error-message'>{error}</p>}

      <div className="form-card-container">
        {forms.length === 0 && !error ? (
          <p>Kayıtlı form yok</p>
        ) : (
          forms.map((form) => (
            <div key={form.id} className="form-card">
              <h3>{form.model} - {form.vin}</h3>
              <p>
                <p className="form-text">
                  <strong>Kayıt:</strong>{" "}
                  {form.createdAt ? formatDateTime(form.createdAt) : "Tarih yok"} –{" "}
                </p>

              </p>

              <button onClick={() => navigate(`/form-detail/${form.id}`)}>Detayları Gör</button>
            </div>
          ))
        )

        }
      </div>
    </div>
  )
}

export default FormView
