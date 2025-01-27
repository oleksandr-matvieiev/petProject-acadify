import React, { useEffect, useState } from "react";
import axios from "axios";

const MainPage = () => {
    const [subjects, setSubjects] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchSubjects = async () => {
            try {
                const token = localStorage.getItem("Token");
                const response = await axios.get("http://localhost:8080/api/subject/available", {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });
                setSubjects(response.data);
            } catch (err) {
                setError("Failed to fetch subjects. Please try again.");
            } finally {
                setLoading(false);
            }
        };

        fetchSubjects();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>{error}</div>;
    }

    return (
        <div className="flex">
            {/* Sidebar for subjects */}
            <aside className="w-1/4 bg-gray-100 p-4 border-r h-screen">
                <h2 className="text-xl font-bold mb-4">Your Subjects</h2>
                <ul>
                    {subjects.map((subject) => (
                        <li
                            key={subject.id}
                            className="p-2 mb-2 bg-white rounded shadow hover:bg-gray-200 cursor-pointer"
                        >
                            {subject.name}
                        </li>
                    ))}
                </ul>
            </aside>

            <main className="flex-1 p-6">
                <h1 className="text-2xl font-bold">Welcome!</h1>
                <p>Select a subject from the sidebar to view details or tasks.</p>
            </main>
        </div>
    );
};

export default MainPage;
