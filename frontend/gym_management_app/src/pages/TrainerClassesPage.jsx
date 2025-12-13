import React, { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import DashboardLayout from "../components/DashboardLayout";
import { TRAINER_MENU } from "../constants/menuItems";
import { useNavigate } from "react-router-dom";

export default function TrainerClassesPage() {
  const { token, userId } = useAuth();
  const [classes, setClasses] = useState([]);
  const [filteredClasses, setFilteredClasses] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchClasses = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/group_classes/notdetailed/${userId}`,
          {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );

        if (!response.ok) {
          throw new Error("Failed to fetch trainer classes");
        }

        const data = await response.json();
        const list = Array.isArray(data) ? data : [];

        setClasses(list);
        setFilteredClasses(list);
      } catch (err) {
        console.error("Error fetching trainer classes:", err);
      } finally {
        setLoading(false);
      }
    };

    if (token && userId) fetchClasses();
  }, [token, userId]);

  // 🔍 SEARCH (name, description, date)
  useEffect(() => {
    const q = search.toLowerCase();

    setFilteredClasses(
      classes.filter((cls) => {
        const dateString = new Date(cls.date_time)
          .toLocaleString()
          .toLowerCase();

        return (
          cls.name.toLowerCase().includes(q) ||
          cls.description.toLowerCase().includes(q) ||
          dateString.includes(q)
        );
      })
    );
  }, [search, classes]);

  return (
    <DashboardLayout menuItems={TRAINER_MENU}>
      <div className="p-6">
        <h1 className="text-3xl font-bold text-foreground mb-6">
          My Group Classes
        </h1>

        <button
          onClick={() => navigate("/dashboard")}
          className="mb-4 bg-primary text-primary-foreground px-4 py-2 rounded-lg hover:opacity-90 transition"
        >
          ← Back to Dashboard
        </button>

        {/* SEARCH */}
        <input
          type="text"
          placeholder="Search by name, description or date..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          className="mb-4 block w-full md:w-1/3 px-3 py-2 border border-border rounded bg-input"
        />

        {loading ? (
          <p className="text-muted-foreground">Loading classes...</p>
        ) : filteredClasses.length === 0 ? (
          <p className="text-muted-foreground">You have no scheduled classes.</p>
        ) : (
          <div className="rounded-lg shadow bg-card overflow-hidden">
            <table className="w-full border-collapse">
              <thead className="bg-muted">
                <tr>
                  <th className="p-3 text-left">Name</th>
                  <th className="p-3 text-left">Description</th>
                  <th className="p-3 text-left">Participants</th>
                  <th className="p-3 text-left">Date & Time</th>
                  <th className="p-3 text-left">Max Participants</th>
                </tr>
              </thead>

              <tbody>
                {filteredClasses.map((cls, index) => (
                  <tr
                    key={index}
                    className="border-b border-border hover:bg-muted/60 transition"
                  >
                    <td className="p-3">{cls.name}</td>
                    <td className="p-3">{cls.description}</td>

                    <td className="p-3">
                      {cls.clients.length} enrolled
                      {cls.clients.length > 0 && (
                        <div className="text-xs text-muted-foreground mt-1">
                          {cls.clients.map((c, i) => (
                            <span key={i}>
                              {c.name} {c.surname}
                              {i < cls.clients.length - 1 ? ", " : ""}
                            </span>
                          ))}
                        </div>
                      )}
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
