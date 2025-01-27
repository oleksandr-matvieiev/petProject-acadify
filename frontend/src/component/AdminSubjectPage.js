import { useState } from "react";
import axios from "axios";

const AdminSubjectsPage = () => {
    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [groupName, setGroupName] = useState("");

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

    return (
        <div>
            <h1>Create new subject</h1>
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
                        GroupName:
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
    );
};

export default AdminSubjectsPage;