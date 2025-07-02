import React, { useEffect, useState } from 'react'
import "../components/FormView.css"

function FormView() {

  const [forms, setForms] = useState([]);
  const [error, setError] = useState('')

  useEffect(() => {
     const fetchForms = async () => {
      try {
        const auth = localStorage.getItem("auth");

        if (!auth) {
          setError("Giriş bilgisi bulunamadı. Lütfen tekrar giriş yapın.");
          return;
        }

        const response = await fetch('http://localhost:8080/api/pdi-form/list', {
          method: 'GET',
          headers: {
            'Authorization': `Basic ${auth}`
          }
        });

        if (response.ok) {
         
          const data = await response.json();
           console.log("Gelen form listesi:", data);
          setForms(data);
        } else if (response.status === 403) {
          setError("Yetkiniz yok. Bu formları görüntüleyemezsiniz.");
        } else {
          setError(`Sunucu hatası: ${response.status}`);
        }
      } catch (err) {
        console.error('Fetch error:', err);
        setError("Veriler getirilirken hata oluştu.");
      }
    };

    fetchForms();
  }, []);

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
              <p><strong>PDI Yeri:</strong> {form.pdiYeri}</p>
              <p><strong>Kilometre:</strong> {form.kmBilgisi}</p>
              <p><strong>Tarih:</strong> {form.kontrolTarihi}</p>
              <p><strong>Oluşturan:</strong> {form.officer?.name ?? "Bilinmiyor"}</p>
              <button onClick={() => console.log('Form detayına git:', form.id)}>Detayları Gör</button>
            </div>
          ))
        )
      
      }
      </div>
    </div>
  )
}

export default FormView
