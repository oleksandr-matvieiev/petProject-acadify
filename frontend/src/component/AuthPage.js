import {useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";

const AuthPage = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const navigate = useNavigate();
    const baseApiUrl = 'http://localhost:8080/api/auth'

    const handleLogin = async (e) => {
        e.preventDefault();
        setError("");
        try {
            const response = await axios.post(`${baseApiUrl}/login`, {email, password});
            const token = response.data;

            if (token) {
                localStorage.setItem('Token', token);
                console.log(token);
                alert("Login successful!");
                navigate("/")
            } else {
                alert('Invalid response from server. No token provided.');
            }
        } catch (err) {
            setError("Login failed. Please check your credentials and try again.");
            console.error('Login failed:', err);
        }
    };
    return (
        <div style={{textAlign: "center", marginTop: "50px"}}>
            <h1>Login page</h1>
            <form onSubmit={handleLogin}>
                <div>
                    <input
                        type="text"
                        placeholder="Enter your email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                        style={{margin: "10px 0", padding: "8px", width: "300px"}}
                    />
                </div>
                <div>
                    <input
                        type="password"
                        placeholder="Enter your password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        style={{margin: "10px 0", padding: "8px", width: "300px"}}
                    />
                </div>
                {error && <p style={{color: "red"}}>{error}</p>} {/* Відображення помилки */}
                <button
                    type="submit"
                    style={{
                        padding: "10px 20px",
                        backgroundColor: "#007BFF",
                        color: "#FFF",
                        border: "none",
                        borderRadius: "5px",
                        cursor: "pointer",
                    }}
                >
                    Login
                </button>
            </form>
        </div>
    );
};

export default AuthPage;