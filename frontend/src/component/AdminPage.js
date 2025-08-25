import {useNavigate} from "react-router-dom";

const AdminPage = () => {
    const navigate = useNavigate();

    const handleRegisterStudent = () => {navigate("/register-student");};


    const handleRegisterTeacher = () => {navigate("/register-teacher");};
    const handleCreateNewSubject=()=>{navigate("/create-subject");};

    return (
        <div style={{padding:"20px", fontFamily:"Arial,sans-serif"}}>
            <h1 style={{textAlign:"center",color:"#333",marginBottom:"40px"}}>
            Admin Dashboard
            </h1>

            <div style={{
                maxWidth:"600px",
                margin:"0 auto",
                display:"flex",
                flexDirection:"column",
                gap:"20px"
            }}>
                <button
                    onClick={handleRegisterStudent}
                    style={buttonStyle("#28a745")}>
                    Register new Student
                </button>

                <button onClick={handleRegisterTeacher}
                        style={buttonStyle("#007bff")}>
                        Register new Teacher
                </button>

                <button onClick={handleCreateNewSubject}
                        style={buttonStyle("#ffc107")}>
                    Create new Subject
                </button>
            </div>
        </div>
    );
};

const buttonStyle =(bgColor, textColor = "#fff")=>({
    padding: "12px 20px",
    backgroundColor:bgColor,
    color:textColor,
    border:"none",
    borderRadius:"5px",
    cursor:"pointer",
    fontSize:"16 px",
    fontWeight:"bold",
    transition: "background-color 0.3s",
    boxShadow: "0 2px 6px rgba(0,0,0,0.1)"
});
export default AdminPage;