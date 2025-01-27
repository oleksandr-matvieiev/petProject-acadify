import { useEffect, useState } from "react";
import axios from "axios";

const AdminSubjectsPage = () => {
    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [groupName, setGroupName] = useState("");

    const [email, setEmail] = useState("");
    const [subjectName, setSubjectName] = useState("");

    const [subjects, setSubjects] = useState([]);

    const baseApiUrl = "http://localhost:8080/api/subject";
    const token = localStorage.getItem("Token");

    const handleCreateNewSubject = async (e) => {
        e.preventDefault();
        if (!token) {
            console.error("No token found");
            return;
        }
        try {
            const response = await axios.post(`${baseApiUrl}/create`, null, {
                params: { name, description, groupName },
                headers: {
                    Authorization: `Bearer ${token}`
                },
            });
            console.log("Subject created successfully:", response.data);
            setName("");
            setDescription("");
            setGroupName("");
        } catch (error) {
            console.error("Error while handling creation of new subject: ", error);
        }
    };

    const handleAddTeacherToSubject = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(`${baseApiUrl}/add/teacher`, null, {
                params: { email, subjectName },
                headers: {
                    Authorization: `Bearer ${token}`
                },
            });
            console.log("Teacher added successfully:", response.data);
            setEmail("");
            setSubjectName("");
        } catch (error) {
            console.error("Error while handling adding of teacher to subject: ", error);
        }
    };

    const handleAddStudentToSubject = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(`${baseApiUrl}/add/student`, null, {
                params: { email, subjectName },
                headers: {
                    Authorization: `Bearer ${token}`
                },
            });
            console.log("Student added successfully:", response.data);
            setEmail("");
            setSubjectName("");
        } catch (error) {
            console.error("Error while handling adding of student to subject: ", error);
        }
    };

    useEffect(() => {
        const fetchSubjects = async () => {
            try {
                const response = await axios.get(`${baseApiUrl}/allSubjects`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    },
                });
                setSubjects(response.data);
            } catch (error) {
                console.error("Failed to fetch subjects. Please try again.");
            }
        };
        fetchSubjects();
    }, []);

    return (
        <div style={{ padding: "20px", fontFamily: "Arial, sans-serif" }}>
            <h1 style={{ textAlign: "center", color: "#333" }}>Admin Subjects Page</h1>

            <div style={{ marginBottom: "40px" }}>
                <h2>Create New Subject</h2>
                <form onSubmit={handleCreateNewSubject} style={{ maxWidth: "600px", margin: "0 auto" }}>
                    <div style={{ marginBottom: "20px" }}>
                        <label htmlFor="name" style={{ display: "block", marginBottom: "5px" }}>
                            Name:
                        </label>
                        <input
                            type="text"
                            id="name"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            required
                            style={{
                                width: "100%",
                                padding: "10px",
                                border: "1px solid #ccc",
                                borderRadius: "5px",
                            }}
                        />
                    </div>
                    <div style={{ marginBottom: "20px" }}>
                        <label htmlFor="description" style={{ display: "block", marginBottom: "5px" }}>
                            Description:
                        </label>
                        <textarea
                            id="description"
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
                            rows="5"
                            style={{
                                width: "100%",
                                padding: "10px",
                                border: "1px solid #ccc",
                                borderRadius: "5px",
                            }}
                        ></textarea>
                    </div>
                    <div style={{ marginBottom: "20px" }}>
                        <label htmlFor="groupName" style={{ display: "block", marginBottom: "5px" }}>
                            Group Name:
                        </label>
                        <input
                            type="text"
                            id="groupName"
                            value={groupName}
                            onChange={(e) => setGroupName(e.target.value)}
                            required
                            style={{
                                width: "100%",
                                padding: "10px",
                                border: "1px solid #ccc",
                                borderRadius: "5px",
                            }}
                        />
                    </div>
                    <button
                        type="submit"
                        style={{
                            padding: "10px 20px",
                            backgroundColor: "#28a745",
                            color: "white",
                            border: "none",
                            borderRadius: "5px",
                            cursor: "pointer",
                        }}
                    >
                        Create New Subject
                    </button>
                </form>
            </div>

            <div style={{ marginBottom: "40px" }}>
                <h2>Add Teacher to Subject</h2>
                <form onSubmit={handleAddTeacherToSubject} style={{ maxWidth: "600px", margin: "0 auto" }}>
                    <div style={{ marginBottom: "20px" }}>
                        <label htmlFor="email" style={{ display: "block", marginBottom: "5px" }}>
                            Teacher Email:
                        </label>
                        <input
                            type="email"
                            id="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                            style={{
                                width: "100%",
                                padding: "10px",
                                border: "1px solid #ccc",
                                borderRadius: "5px",
                            }}
                        />
                    </div>
                    <div style={{ marginBottom: "20px" }}>
                        <label htmlFor="subjectName" style={{ display: "block", marginBottom: "5px" }}>
                            Subject Name:
                        </label>
                        <input
                            type="text"
                            id="subjectName"
                            value={subjectName}
                            onChange={(e) => setSubjectName(e.target.value)}
                            required
                            style={{
                                width: "100%",
                                padding: "10px",
                                border: "1px solid #ccc",
                                borderRadius: "5px",
                            }}
                        />
                    </div>
                    <button
                        type="submit"
                        style={{
                            padding: "10px 20px",
                            backgroundColor: "#007bff",
                            color: "white",
                            border: "none",
                            borderRadius: "5px",
                            cursor: "pointer",
                        }}
                    >
                        Add Teacher
                    </button>
                </form>
            </div>

            <div style={{ marginBottom: "40px" }}>
                <h2>Add Student to Subject</h2>
                <form onSubmit={handleAddStudentToSubject} style={{ maxWidth: "600px", margin: "0 auto" }}>
                    <div style={{ marginBottom: "20px" }}>
                        <label htmlFor="email" style={{ display: "block", marginBottom: "5px" }}>
                            Student Email:
                        </label>
                        <input
                            type="email"
                            id="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                            style={{
                                width: "100%",
                                padding: "10px",
                                border: "1px solid #ccc",
                                borderRadius: "5px",
                            }}
                        />
                    </div>
                    <div style={{ marginBottom: "20px" }}>
                        <label htmlFor="subjectName" style={{ display: "block", marginBottom: "5px" }}>
                            Subject Name:
                        </label>
                        <input
                            type="text"
                            id="subjectName"
                            value={subjectName}
                            onChange={(e) => setSubjectName(e.target.value)}
                            required
                            style={{
                                width: "100%",
                                padding: "10px",
                                border: "1px solid #ccc",
                                borderRadius: "5px",
                            }}
                        />
                    </div>
                    <button
                        type="submit"
                        style={{
                            padding: "10px 20px",
                            backgroundColor: "#ffc107",
                            color: "black",
                            border: "none",
                            borderRadius: "5px",
                            cursor: "pointer",
                        }}
                    >
                        Add Student
                    </button>
                </form>
            </div>

            <div>
                <h2>All Subjects</h2>
                <ul style={{ listStyleType: "none", padding: "0" }}>
                    {subjects.map((subject, index) => (
                        <li key={index} style={{ padding: "10px", borderBottom: "1px solid #ccc" }}>
                            <strong>{subject.name}</strong> - {subject.description} (Group: {subject.groupName})
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default AdminSubjectsPage;