import {useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";


const AuthPage = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const navigate = useNavigate();
    const baseApiUrl = "http://localhost:8080/api/auth";

    const handleLogin = async (e) => {
        e.preventDefault();
        setError("");

        try {
            const response = await axios.post(`${baseApiUrl}/login`, {
                email: "email",
                password: "password"
            });

            const token = response.data;

            if (token) {
                localStorage.setItem("Token", token);
                alert("Login successfully1");
                navigate("/");
            } else {
                alert("Invalid response from server. No token provided.");
            }
        } catch (err) {
            setError("Login failed. Please check your credentials.");
            console.error("Login failed:", err);
        }
    };

    return (
        <div style={styles.container}>
            <h1 style={styles.title}>Login Page</h1>
            <form onSubmit={handleLogin} style={styles.form}>
                <input type="text"
                       placeholder="Enter your Email"
                       value={email}
                       onChange={(e) => setEmail(e.target.value)}
                       required
                       style={styles.input}
                />
                <input type="password"
                       placeholder="Enter your password"
                       value={password}
                       onChange={(e) => setPassword(e.target.value)}
                       required
                       style={styles.input}
                />
                {error && <p style={styles.error}>error</p>}

                <button type="submit" style={styles.button}>
                    Login
                </button>
            </form>
        </div>
    );
};
const styles = {
    container: {
        maxWidth: "400px",
        margin: "80px auto",
        padding: "20px",
        border: "1px solid #ddd",
        borderRadius: "10px",
        boxShadow: "0,0,0,0.1",
        textAlign: "center",
        fontFamily: "Arial, sans-serif"

    },
    title: {marginBottom: "20px", color: "#333"},
    form: {display: "flex", flexDirection: "column", gap: "15px"},
    input: {
        padding: "12px",
        border: "1px solid #ccc",
        borderRadius: "5px",
        fontSize: "14px"
    },
    button: {
        padding: "12px",
        backgroundColor: "#007bff",
        color: "#fff",
        border: "none",
        borderRadius: "5px",
        cursor: "pointer",
        fontWeight: "bold",
        transition: "background 0.3s"
    },
    error: {color: "red", fontSize: "14px"}

};
export {AuthPage};