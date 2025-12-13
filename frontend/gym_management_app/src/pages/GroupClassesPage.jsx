import React, { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import DashboardLayout from "../components/DashboardLayout";
import { RECEPTIONIST_MENU } from "../constants/menuItems";
import PageHeader from "../components/PageHeader";

export default function GroupClassesPage() {
  const { token } = useAuth();

  const [allClasses, setAllClasses] = useState([]);
  const [filteredClasses, setFilteredClasses] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchClasses = async () => {
      try {
        const res = await fetch(
          "http://localhost:8080/api/group_classes/notdetailed",
          {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json"
            }
          }
        );

        if (!res.ok) {
          throw new Error("Failed to fetch group classes");
        }

        const data = await res.json();
        const list = Array.isArray(data) ? data : [];

        setAllClasses(list);
        setFilteredClasses(list);
      } catch (err) {
        console.error("Error fetching classes:", err);
        setAllClasses([]);
        setFilteredClasses([]);
      } finally {
        setLoading(false);
      }
    };

    if (token) fetchClasses();
  }, [token]);

  // Client-side search
  useEffect(() => {
    const q = search.toLowerCase();

    setFilteredClasses(
      allClasses.filter((cls) =>
        cls.name.toLowerCase().includes(q) ||
        cls.description.toLowerCase().includes(q) ||
        cls.trainer_name.toLowerCase().includes(q) ||
        cls.trainer_surname.toLowerCase().includes(q)
      )
    );
  }, [search, allClasses]);

  return (
    <DashboardLayout menuItems={RECEPTIONIST_MENU}>
      <div>
        <PageHeader
          title="Group Classes"
          showSearch
          searchValue={search}
          onSearchChange={setSearch}
          searchPlaceholder="Search by class name, description or trainer..."
        />

        {loading ? (
          <p className="text-muted-foreground">Loading group classes...</p>
        ) : filteredClasses.length === 0 ? (
          <p className="text-muted-foreground">No group classes found.</p>
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
                  <th className="p-3 text-left">Max</th>
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
                      {cls.trainer_name} {cls.trainer_surname}
                    </td>
                    <td className="p-3">
                      {cls.clients.length}
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
