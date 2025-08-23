import { useEffect, useState } from "react";
import axios from "axios";

const AdminSubjectsPage = () => {
    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [groupName, setGroupName] = useState("");

    const [teacherEmail, setTeacherEmail] = useState("");
    const [teacherSubjectName, setTeacherSubjectName] = useState("");

    const [studentEmail, setStudentEmail] = useState("");
    const [studentSubjectName, setStudentSubjectName] = useState("");

    const [students, setStudents] = useState([]);
    const [teachers, setTeachers] = useState([]);

    const [subjects, setSubjects] = useState([]);

    const [groups, setGroups] = useState([]);

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
                params: {
                    email:teacherEmail,
                    subjectName: teacherSubjectName
                },
                headers: {
                    Authorization: `Bearer ${token}`
                },
            });
            console.log("Teacher added successfully:", response.data);
            setTeacherEmail("");
            setTeacherSubjectName("");
        } catch (error) {
            console.error("Error while handling adding of teacher to subject: ", error);
        }
    };

    const handleAddStudentToSubject = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(`${baseApiUrl}/add/student`, null, {
                params: {
                    email: studentEmail,
                    subjectName: studentSubjectName
                },
                headers: {
                    Authorization: `Bearer ${token}`
                },
            });
            console.log("Student added successfully:", response.data);
            setStudentEmail("");
            setStudentSubjectName("");
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

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const [studentRes, teacherRes] = await Promise.all([
                    axios.get("http://localhost:8080/api/user/students", {
                        headers: { Authorization: `Bearer ${token}` },
                    }),
                    axios.get("http://localhost:8080/api/user/teachers", {
                        headers: { Authorization: `Bearer ${token}` },
                    }),
                ]);
                setStudents(studentRes.data);
                setTeachers(teacherRes.data);
            } catch (error) {
                console.error("Failed to fetch users:", error);
            }
        };

        fetchUsers();
    }, []);

    useEffect(() => {
        const fetchGroups = async () => {
            try {
                const response = await axios.get("http://localhost:8080/api/group/get-all", {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                setGroups(response.data);
            } catch (error) {
                console.error("Failed to fetch groups:", error);
            }
        };

        fetchGroups();
    }, []);



    return (
        <div style={{ padding: "20px", fontFamily: "Arial, sans-serif" }}>
            <h1 style={{ textAlign: "center", color: "#333" }}>Admin Subjects Page</h1>

            <div style={{ marginBottom: "40px" }}>
                <h2 style={{textAlign:"center",marginBottom:"16px"}}>Create New Subject</h2>
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
                            Group:
                        </label>
                        <select
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
                        >
                            <option value="">Select group</option>
                            {groups.map((group) => (
                                <option key={group.id} value={group.name}>
                                    {group.name}
                                </option>
                            ))}
                        </select>
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

            <div style={{ marginBottom: 40 }}>
                <h2 style={{ textAlign: "center", marginBottom: 24 }}>Add Teacher to Subject</h2>
                <form onSubmit={handleAddTeacherToSubject} style={{ maxWidth: 600, margin: "0 auto" }}>

                    <div style={{ marginBottom: 20 }}>
                        <label htmlFor="email" style={{ display: "block", marginBottom: 8 }}>
                            Teacher:
                        </label>
                        <select
                            id="email"
                            value={teacherEmail}
                            onChange={(e) => setTeacherEmail(e.target.value)}
                            required
                            style={{
                                width: "100%",
                                padding: 10,
                                border: "1px solid #ccc",
                                borderRadius: 5,
                            }}
                        >
                            <option value="" disabled>Select a teacher</option>
                            {teachers.map((teacher) => (
                                <option key={teacher.email} value={teacher.email}>
                                    {teacher.firstName} {teacher.lastName} - {teacher.email}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div style={{ marginBottom: 20 }}>
                        <label htmlFor="subjectName" style={{ display: "block", marginBottom: 8 }}>
                            Subject:
                        </label>
                        <select
                            id="subjectName"
                            value={teacherSubjectName}
                            onChange={(e) => setTeacherSubjectName(e.target.value)}
                            required
                            style={{
                                width: "100%",
                                padding: 10,
                                border: "1px solid #ccc",
                                borderRadius: 5,
                            }}
                        >
                            <option value="" disabled>Select a subject</option>
                            {subjects.map((subject) => (
                                <option key={subject.name} value={subject.name}>
                                    {subject.name}
                                </option>
                            ))}
                        </select>
                    </div>

                    <button
                        type="submit"
                        style={{
                            padding: "10px 20px",
                            backgroundColor: "#007bff",
                            color: "white",
                            border: "none",
                            borderRadius: 5,
                            cursor: "pointer",
                        }}
                    >
                        Add Teacher
                    </button>
                </form>
            </div>



            <div style={{ marginBottom: "40px" }}>
                <h2 style={{textAlign: "center", marginBottom: "16px"}}>Add Student to Subject</h2>
                <form onSubmit={handleAddStudentToSubject} style={{ maxWidth: "600px", margin: "0 auto" }}>
                    <div style={{ marginBottom: "20px" }}>
                        <label htmlFor="email" style={{ display: "block", marginBottom: "5px" }}>
                            Student:
                        </label>
                        <select
                            id="email"
                            value={studentEmail}
                            onChange={(e) => setStudentEmail(e.target.value)}
                            required
                            style={{
                                width: "100%",
                                padding: "10px",
                                border: "1px solid #ccc",
                                borderRadius: "5px",
                            }}
                        >
                            <option value="" disabled>Select a student</option>
                            {students.map((student) => {
                                return (
                                    <option key={student.email} value={student.email}>
                                        {student.firstName} {student.lastName} ({student.groupName}) - {student.email}
                                    </option>
                                );
                            })}


                        </select>
                    </div>

                    <div style={{ marginBottom: "20px" }}>
                        <label htmlFor="subjectName" style={{ display: "block", marginBottom: "5px" }}>
                            Subject:
                        </label>
                        <select
                            id="subjectName"
                            value={studentSubjectName}
                            onChange={(e) => setStudentSubjectName(e.target.value)}
                            required
                            style={{
                                width: "100%",
                                padding: "10px",
                                border: "1px solid #ccc",
                                borderRadius: "5px",
                            }}
                        >
                            <option value="" disabled>Select a subject</option>
                            {subjects.map((subject) => (
                                <option key={subject.name} value={subject.name}>
                                    {subject.name}
                                </option>
                            ))}
                        </select>
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
                <h2 style={{textAlign:"center",marginBottom:"16px"}}>All Subjects</h2>
                <ul style={{ listStyleType: "none", padding: 0 }}>
                    {subjects.map((subject, index) => {
                        const subjectGroups = groups.filter(group => subject.groupsIds.includes(group.id));

                        return (
                            <li key={index} style={{ padding: "10px", borderBottom: "1px solid #ccc" }}>
                                <strong>{subject.name}</strong> - {subject.description} (Groups:{" "}
                                {subjectGroups.length > 0
                                    ? subjectGroups.map(g => g.name).join(", ")
                                    : "No groups"}
                                )
                            </li>
                        );
                    })}
                </ul>

            </div>
        </div>
    );
};

export default AdminSubjectsPage;