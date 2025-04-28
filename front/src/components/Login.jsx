import React, { useState } from 'react';
import './Login.css';
import { useNavigate } from 'react-router-dom';

function Login({ onLogin }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const navigate = useNavigate();

    const handleSubmit = (event) => {
        event.preventDefault();
        if (username === '' || password === '') {
            setError('Kullanıcı adı ve şifre boş bırakılamaz!');
            return;
        }


        if (username === 'admin' && password === 'admin123') {
            onLogin('admin');
            navigate("/pdi-form")
        } else if (username === 'calisan' && password === 'calisan123') {
            onLogin('calisan');
        } else {
            setError('Kullanıcı adı veya şifre yanlış!');
        }
    };

    return (
        <div className="login-container">
            <h2>Giriş Yap</h2>
            <form onSubmit={handleSubmit}>
                <div className="input-group">
                    <label>Kullanıcı Adı</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        placeholder="Kullanıcı Adınızı Girin"
                    />
                </div>
                <div className="input-group">
                    <label>Şifre</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder="Şifrenizi Girin"
                    />
                </div>
                {error && <div className="error-message">{error}</div>}
                <button type="submit">Giriş Yap</button>
            </form>
        </div>
    );
}

export default Login;
