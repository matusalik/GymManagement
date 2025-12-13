import React, { useEffect, useState } from "react";
import DashboardLayout from "../components/DashboardLayout";
import { RECEPTIONIST_MENU } from "../constants/menuItems";
import { useAuth } from "../context/AuthContext";
import PageHeader from "../components/PageHeader";

export default function TrainersPage() {
  const { token } = useAuth();

  const [allTrainers, setAllTrainers] = useState([]);
  const [filteredTrainers, setFilteredTrainers] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchTrainers = async () => {
      try {
        const res = await fetch("http://localhost:8080/api/trainers", {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
          }
        });

        if (!res.ok) {
          throw new Error("Failed to fetch trainers");
        }

        const data = await res.json();
        const list = Array.isArray(data) ? data : [];

        setAllTrainers(list);
        setFilteredTrainers(list);
      } catch (err) {
        console.error("Error fetching trainers:", err);
        setAllTrainers([]);
        setFilteredTrainers([]);
      } finally {
        setLoading(false);
      }
    };

    if (token) fetchTrainers();
  }, [token]);

  // Client-side search
  useEffect(() => {
    const q = search.toLowerCase();

    setFilteredTrainers(
      allTrainers.filter((t) =>
        t.username.toLowerCase().includes(q) ||
        t.first_name.toLowerCase().includes(q) ||
        t.last_name.toLowerCase().includes(q) ||
        t.email.toLowerCase().includes(q) ||
        (t.bio && t.bio.toLowerCase().includes(q))
      )
    );
  }, [search, allTrainers]);

  return (
    <DashboardLayout menuItems={RECEPTIONIST_MENU}>
      <div className="p-6">
        <PageHeader
          title="Trainers"
          showSearch
          searchValue={search}
          onSearchChange={setSearch}
          searchPlaceholder="Search by username, name, email or bio..."
        />

        {loading ? (
          <p className="text-muted-foreground">Loading trainers...</p>
        ) : filteredTrainers.length === 0 ? (
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
                {filteredTrainers.map((t, index) => (
                  <tr
                    key={index}
                    className="border-b border-border hover:bg-muted/60 transition"
                  >
                    <td className="p-3">{t.username}</td>
                    <td className="p-3">
                      {t.first_name} {t.last_name}
                    </td>
                    <td className="p-3">{t.phone}</td>
                    <td className="p-3">{t.email}</td>
                    <td className="p-3">{t.bio}</td>
                    <td className="p-3">{t.status}</td>
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
