import { useNavigate } from 'react-router-dom';
import "../components/SelectionScreen.css";
import { LogOut } from './Logout';

function SelectionScreen() {
  const navigate = useNavigate();

  const role = localStorage.getItem("role"); // ADMIN veya OFFICER gelecek

  return (
    <div>
      <div className='logout'>
        <button onClick={LogOut}>Çıkış Yap</button>
      </div>

      <div className='form-selection'>
        <div className='buttonContainer'>
          <button onClick={() => navigate("/view")}>Form Görüntüle</button>
        </div>
        <div className='buttonContainer'>
          <button onClick={() => navigate("/pdi-form")}>Form Gönder</button>
        </div>

    
        {role === "ADMIN" && (
          <div className='buttonContainer'>
            <button onClick={() => navigate("/user-management")}>Kullanıcı Oluştur / Sil</button>
          </div>
        )}
      </div>
    </div>
  );
}

export default SelectionScreen;
