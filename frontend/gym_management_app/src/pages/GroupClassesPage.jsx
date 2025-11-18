import { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import DashboardLayout from "../components/DashboardLayout";
import { RECEPTIONIST_MENU } from "../constants/menuItems";
import { useNavigate } from "react-router-dom";

export default function GroupClassesPage() {
  const { token } = useAuth();
  const [classes, setClasses] = useState([]);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchClasses = async () => {
      try {
        const response = await fetch(
          "http://localhost:8080/api/group_classes/notdetailed",
          {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );

        if (!response.ok) {
          throw new Error("Failed to fetch classes");
        }

        const data = await response.json();
        setClasses(Array.isArray(data) ? data : []);
      } catch (err) {
        console.error("Error fetching group classes:", err);
      } finally {
        setLoading(false);
      }
    };

    if (token) fetchClasses();
  }, [token]);

  return (
    <DashboardLayout menuItems={RECEPTIONIST_MENU}>
      <div>
        <h1 className="text-3xl font-bold text-foreground mb-6">
          Group Classes
        </h1>

        {/* Back Button */}
        <button
          onClick={() => navigate("/dashboard")}
          className="mb-4 bg-primary text-primary-foreground px-4 py-2 rounded-lg hover:opacity-90 transition"
        >
          ← Back to Dashboard
        </button>

        {loading ? (
          <p className="text-muted-foreground">Loading group classes...</p>
        ) : classes.length === 0 ? (
          <p className="text-muted-foreground">No classes found.</p>
        ) : (
          <div className="rounded-lg shadow bg-card overflow-hidden">
            <table className="w-full border-collapse">
              <thead className="bg-muted">
                <tr>
                  <th className="p-3 text-left">Name</th>
                  <th className="p-3 text-left">Description</th>
                  <th className="p-3 text-left">Trainer</th>
                  <th className="p-3 text-left">Participants</th>
                  <th className="p-3 text-left">Date & Time</th>
                  <th className="p-3 text-left">Max Participants</th>
                </tr>
              </thead>

              <tbody>
                {classes.map((cls, index) => (
                  <tr
                    key={index}
                    className="border-b border-border hover:bg-muted/60 transition"
                  >
                    <td className="p-3">{cls.name}</td>
                    <td className="p-3">{cls.description}</td>
                    <td className="p-3">
                      {cls.trainer_name} {cls.trainer_surname}
                    </td>
                    <td className="p-3">
                      {cls.clients.length} enrolled
                      <div className="text-xs text-muted-foreground mt-1">
                        {cls.clients.map((c, i) => (
                          <span key={i}>
                            {c.name} {c.surname}
                            {i < cls.clients.length - 1 ? ", " : ""}
                          </span>
                        ))}
                      </div>
                    </td>
                    <td className="p-3">
                      {new Date(cls.date_time).toLocaleString()}
                    </td>
                    <td className="p-3">{cls.max_participants}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </DashboardLayout>
  );
}
