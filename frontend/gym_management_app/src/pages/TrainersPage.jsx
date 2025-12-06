import React, { useEffect, useState } from "react";
import DashboardLayout from "../components/DashboardLayout";
import { RECEPTIONIST_MENU } from "../constants/menuItems";
import { useAuth } from "../context/AuthContext";

export default function TrainersPage() {
  const { token } = useAuth();
  const [trainers, setTrainers] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchTrainers = async () => {
      try {
        const res = await fetch("http://localhost:8080/api/trainers", {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        });

        const data = await res.json();
        setTrainers(Array.isArray(data) ? data : []);
      } catch (err) {
        console.error("Error fetching trainers:", err);
        setTrainers([]);
      } finally {
        setLoading(false);
      }
    };

    if (token) fetchTrainers();
  }, [token]);

  return (
    <DashboardLayout menuItems={RECEPTIONIST_MENU}>
      <div className="p-6">
        <h1 className="text-3xl font-bold text-foreground mb-6">
          Trainers
        </h1>
        
        {loading ? (
          <p className="text-muted-foreground">Loading trainers...</p>
        ) : trainers.length === 0 ? (
          <p className="text-muted-foreground">No trainers found.</p>
        ) : (
          <div className="rounded-lg shadow bg-card overflow-hidden">
            <table className="w-full border-collapse">
              <thead className="bg-muted">
                <tr>
                  <th className="p-3 text-left">Username</th>
                  <th className="p-3 text-left">Name</th>
                  <th className="p-3 text-left">Phone</th>
                  <th className="p-3 text-left">Email</th>
                  <th className="p-3 text-left">Bio</th>
                  <th className="p-3 text-left">Status</th>
                </tr>
              </thead>

              <tbody>
                {trainers.map((t, index) => (
                  <tr
                    key={index}
                    className="border-b border-border hover:bg-muted/60 transition"
                  >
                    <td className="p-3">{t.username}</td>
                    <td className="p-3">{t.first_name} {t.last_name}</td>
                    <td className="p-3">{t.phone}</td>
                    <td className="p-3">{t.email}</td>
                    <td className="p-3">{t.bio}</td>
                    <td className="p-3">
                      <span
                        className={`px-2 py-1 rounded text-xs font-medium ${
                          t.status === "ACTIVE"
                            ? "bg-primary/20 text-primary"
                            : "bg-destructive/20 text-destructive"
                        }`}
                      >
                        {t.status}
                      </span>
                    </td>                       
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
