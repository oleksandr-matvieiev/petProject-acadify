import {useState} from "react";
import axios from "axios";

const token = localStorage.getItem("Token");

const formContainerStyle = {
    maxWidth: "500px",
    margin: "40px auto",
    padding: "20px",
    border: "1px solid #ddd",
    borderRadius: "10px",
    boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
    fontFamily: "Arial, sans-serif",
};

const inputStyle = {
    width: "100%",
    padding: "12px",
    marginBottom: "15px",
    border: "1px solid #ccc",
    borderRadius: "5px",
    fontSize: "14px",
};

const buttonStyle = (bgColor, textColor = "#fff") => ({
    padding: "12px 20px",
    backgroundColor: bgColor,
    color: textColor,
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
    fontSize: "16px",
    fontWeight: "bold",
    transition: "background-color 0.3s",
    boxShadow: "0 2px 6px rgba(0,0,0,0.1)",
    width: "100%",
});


const RegisterStudentPage = () => {

    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        password: "",
        email: "",
        groupName: "",
    });

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(
                "http://localhost:8080/api/auth/register/student",
                formData,
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );
            if (response.status === 201) {
                alert("Student registered successfully!");
                setFormData({
                    firstName: "",
                    lastName: "",
                    password: "",
                    email: "",
                    groupName: "",
                });
            } else {
                alert("Something went wrong!");
            }
        } catch (error) {
            console.error("Error registering student:", error);
            alert("Error registering student. Please try again.");
        }
    };

    return (
        <div style={formContainerStyle}>
            <h1 style={{textAlign: "center", marginBottom: "20px", color: "#333"}}>
                Register New Student
            </h1>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="firstName"
                    placeholder="First Name"
                    value={formData.firstName}
                    onChange={handleChange}
                    style={inputStyle}
                    required
                />
                <input
                    type="text"
                    name="lastName"
                    placeholder="Last Name"
                    value={formData.lastName}
                    onChange={handleChange}
                    style={inputStyle}
                    required
                />
                <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={formData.email}
                    onChange={handleChange}
                    style={inputStyle}
                    required
                />
                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    value={formData.password}
                    onChange={handleChange}
                    style={inputStyle}
                    required
                />
                <input
                    type="text"
                    name="groupName"
                    placeholder="Group Name"
                    value={formData.groupName}
                    onChange={handleChange}
                    style={inputStyle}
                />
                <button type="submit" style={buttonStyle("#28a745")}>
                    Register Student
                </button>
            </form>
        </div>
    );
};

export {RegisterStudentPage};