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
        setError('');

        if (username.trim() === '' || password.trim() === '') {
            setError('Kullanıcı adı ve şifre boş bırakılamaz!');
            return;
        }       

        const encodedCredentials = btoa(`${username}:${password}`);
        localStorage.setItem("auth", encodedCredentials);

try {
    const response = await fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Basic ${encodedCredentials}`
        },
        body: JSON.stringify({
            email: username,
            password: password
        })
    });

    let data = null;
    const contentType = response.headers.get("content-type");

    if (contentType && contentType.includes("application/json")) {
        data = await response.json();
    }

    if (response.ok && data && data.success) {
         localStorage.setItem("role", data.data.role);
        onLogin(data.data.role);
        navigate("/selection");
    } else {
        setError((data && data.message) || `Giriş başarısız! Status: ${response.status}`);
    }

} catch (err) {
    console.error("Login error:", err);
    setError("Sunucuya bağlanılamadı.");
}

    };
    

    return (
        <div className="login-container">
            <h2>Giriş Yap</h2>
            <form onSubmit={handleSubmit}>
                <div className="input-group">
                    <label className='box-text'>Kullanıcı Adı</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        placeholder="Kullanıcı Adınızı Girin"
                    />
                </div>
                <div className="input-group">
                     <label className='box-text'>Şifre</label>
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
