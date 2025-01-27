import {useNavigate} from "react-router-dom";

const AdminPage = () => {
    const navigate = useNavigate();

    const handleRegisterStudent = () => {
        navigate("/register-student");
    };

    const handleRegisterTeacher = () => {
        navigate("/register-teacher");
    };
    const handleCreateNewSubject=()=>{
        navigate("/create-subject");
    }

    return (
        <div style={{textAlign: "center", marginTop: "50px"}}>
            <h1>Registration Options</h1>
            <div style={{margin: "20px 0"}}>
                <button
                    onClick={handleRegisterStudent}
                    style={{
                        padding: "10px 20px",
                        backgroundColor: "#28a745",
                        color: "#fff",
                        border: "none",
                        borderRadius: "5px",
                        marginRight: "10px",
                        cursor: "pointer",
                    }}
                >
                    Register New Student
                </button>
                <button
                    onClick={handleRegisterTeacher}
                    style={{
                        padding: "10px 20px",
                        backgroundColor: "#007bff",
                        color: "#fff",
                        border: "none",
                        borderRadius: "5px",
                        cursor: "pointer",
                    }}
                >
                    Register New Teacher
                </button>
                <button
                    onClick={handleCreateNewSubject}
                    style={{
                        padding: "10px 20px",
                        backgroundColor: "#007bff",
                        color: "#fff",
                        border: "none",
                        borderRadius: "5px",
                        cursor: "pointer",
                    }}
                >
                    Create new subject
                </button>
            </div>
        </div>
    );
};

export default AdminPage;