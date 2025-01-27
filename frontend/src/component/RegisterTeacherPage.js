import {useState} from "react";
import axios from "axios";

const token = localStorage.getItem("Token")
const RegisterTeacherPage = () => {
    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        password: "",
        email: "",
        groupName: ""
    });

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("http://localhost:8080/api/auth/register/teacher", formData, {
                headers: {Authorization: `Bearer ${token}`}
            });
            if (response.status === 201) {
                alert("Teacher registered successfully!");
                setFormData({
                    firstName: "",
                    lastName: "",
                    password: "",
                    email: "",
                    groupName: ""
                });
            } else {
                alert("Something went wrong!");
            }
        } catch (error) {
            console.error("Error registering teacher:", error);
            alert("Error registering teacher. Please try again.");
        }
    };

    return (
        <div>
            <h1>Register New Teacher</h1>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="firstName"
                    placeholder="First Name"
                    value={formData.firstName}
                    onChange={handleChange}
                    required
                />
                <input
                    type="text"
                    name="lastName"
                    placeholder="Last Name"
                    value={formData.lastName}
                    onChange={handleChange}
                    required
                />
                <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                />
                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    value={formData.password}
                    onChange={handleChange}
                    required
                />
                <input
                    type="text"
                    name="groupName"
                    placeholder="Group Name"
                    value={formData.groupName}
                    onChange={handleChange}
                />
                <button type="submit">Register Teacher</button>
            </form>
        </div>
    );
};

const RegisterStudentPage = () => {
    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        password: "",
        email: "",
        groupName: ""
    });

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("http://localhost:8080/api/auth/register/student", formData, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            if (response.status === 201) {
                alert("Student registered successfully!");
                setFormData({
                    firstName: "",
                    lastName: "",
                    password: "",
                    email: "",
                    groupName: ""
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
        <div>
            <h1>Register New Student</h1>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="firstName"
                    placeholder="First Name"
                    value={formData.firstName}
                    onChange={handleChange}
                    required
                />
                <input
                    type="text"
                    name="lastName"
                    placeholder="Last Name"
                    value={formData.lastName}
                    onChange={handleChange}
                    required
                />
                <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                />
                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    value={formData.password}
                    onChange={handleChange}
                    required
                />
                <input
                    type="text"
                    name="groupName"
                    placeholder="Group Name"
                    value={formData.groupName}
                    onChange={handleChange}
                />
                <button type="submit">Register Student</button>
            </form>
        </div>
    );
};

export {RegisterTeacherPage, RegisterStudentPage};
