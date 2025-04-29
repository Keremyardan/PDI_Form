import React, { useState } from 'react';
import './Login.css';
import { useNavigate } from 'react-router-dom';

function Login({ onLogin }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (username === '' || password === '') {
            setError('Kullanıcı adı ve şifre boş bırakılamaz!');
            return;
        }
    
        try {
            const response = await fetch('http://localhost:8080/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    email: username,
                    password: password
                })
            });
    
            const data = await response.json();
    
            if (data.success) {
                onLogin(data.data.role);
                navigate("/pdi-form");
            } else {
                setError(data.message || 'Giriş başarısız.');
            }
        } catch (err) {
            setError("Sunucuya bağlanılamadı.");
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
