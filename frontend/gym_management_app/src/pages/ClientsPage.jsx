import React, { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import DashboardLayout from "../components/DashboardLayout";
import { RECEPTIONIST_MENU } from "../constants/menuItems";
import PageHeader from "../components/PageHeader";

export default function ClientsPage() {
  const { token } = useAuth();

  const [allClients, setAllClients] = useState([]);
  const [filteredClients, setFilteredClients] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchClients = async () => {
      try {
        const res = await fetch("http://localhost:8080/api/clients", {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
          }
        });

        if (!res.ok) {
          throw new Error("Failed to fetch clients");
        }

        const data = await res.json();
        const list = Array.isArray(data) ? data : [];

        setAllClients(list);
        setFilteredClients(list);
      } catch (err) {
        console.error("Error fetching clients:", err);
        setAllClients([]);
        setFilteredClients([]);
      } finally {
        setLoading(false);
      }
    };

    if (token) fetchClients();
  }, [token]);

  // Client-side search
  useEffect(() => {
    const q = search.toLowerCase();

    setFilteredClients(
      allClients.filter((c) =>
        c.username.toLowerCase().includes(q) ||
        c.first_name.toLowerCase().includes(q) ||
        c.last_name.toLowerCase().includes(q) ||
        c.email.toLowerCase().includes(q)
      )
    );
  }, [search, allClients]);

  return (
    <DashboardLayout menuItems={RECEPTIONIST_MENU}>
      <div>
        <PageHeader
          title="Clients"
          showSearch
          searchValue={search}
          onSearchChange={setSearch}
          searchPlaceholder="Search by username, name or email..."
        />

        {loading ? (
          <p className="text-muted-foreground">Loading clients...</p>
        ) : filteredClients.length === 0 ? (
          <p className="text-muted-foreground">No clients found.</p>
        ) : (
          <div className="rounded-lg shadow bg-card overflow-hidden">
            <table className="w-full border-collapse">
              <thead className="bg-muted">
                <tr>
                  <th className="p-3 text-left">Username</th>
                  <th className="p-3 text-left">Name</th>
                  <th className="p-3 text-left">Phone</th>
                  <th className="p-3 text-left">Email</th>
                  <th className="p-3 text-left">Status</th>
                </tr>
              </thead>

              <tbody>
                {filteredClients.map((c, index) => (
                  <tr
                    key={index}
                    className="border-b border-border hover:bg-muted/60 transition"
                  >
                    <td className="p-3">{c.username}</td>
                    <td className="p-3">
                      {c.first_name} {c.last_name}
                    </td>
                    <td className="p-3">{c.phone}</td>
                    <td className="p-3">{c.email}</td>
                    <td className="p-3">{c.status}</td>
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
