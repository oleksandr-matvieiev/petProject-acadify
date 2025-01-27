import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';

import './App.css';
import AuthPage from "./component/AuthPage";
import AdminPage from "./component/AdminPage";
import {RegisterStudentPage, RegisterTeacherPage} from "./component/RegisterTeacherPage";
import MainPage from "./component/MainPage";
import AdminSubjectsPage from "./component/AdminSubjectPage";

function App() {
    return (
        <Router>
            <div>
                <Routes>
                    <Route path={"/login"} element={<AuthPage/>}/>

                    <Route path={"/admin"} element={<AdminPage/>}/>

                    <Route path={"/register-teacher"} element={<RegisterTeacherPage/>}/>

                    <Route path={"/register-student"} element={<RegisterStudentPage/>}/>

                    <Route path={"/"} element={<MainPage/>}/>

                    <Route path={"/create-subject"} element={<AdminSubjectsPage/>}/>


                </Routes>
            </div>


        </Router>
    );
}

export default App;
