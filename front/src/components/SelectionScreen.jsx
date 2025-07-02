import React from 'react';
import { useNavigate } from 'react-router-dom';
import "../components/SelectionScreen.css"



function SelectionScreen() {

    const navigate = useNavigate()



  return (
    <div className='selectionContainer'>
        <div className='buttonContainer'>
      <button onClick={() => navigate("/view")}>Form Görüntüle</button>
      </div>
        <div className='buttonContainer'>
      <button onClick={() => navigate("/pdi-form")}>Form Gönder</button>
      </div>
    </div>
  )
}

export default SelectionScreen
