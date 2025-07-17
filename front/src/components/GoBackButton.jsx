import React from 'react';
import { useNavigate } from 'react-router-dom';
import goBackIcon from '../assets/left-arrow.png';
import './GoBackButton.css';

const GoBackButton = () => {
  const navigate = useNavigate();

  const handleGoBack = (e) => {
    e.stopPropagation();
    navigate(-1);
  };

  return (
    <button className="go-back-button" onClick={handleGoBack}>
      <img src={goBackIcon} alt="Geri" />
    </button>
  );
};

export default GoBackButton;
