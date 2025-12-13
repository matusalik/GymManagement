import React, { useEffect, useState } from "react";
import DashboardLayout from "../components/DashboardLayout";
import { CLIENT_MENU } from "../constants/menuItems";
import { useAuth } from "../context/AuthContext";

export default function ClientAttendancePage() {
  const { token, userId } = useAuth();
  const [attendance, setAttendance] = useState([]);
  const [filteredAttendance, setFilteredAttendance] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);

  const formatDateTime = (iso) => {
    const d = new Date(iso);
    return d.toLocaleString("en-US", {
      dateStyle: "medium",
      timeStyle: "short"
    });
  };

  useEffect(() => {
    const fetchAttendance = async () => {
      try {
        const res = await fetch(
          `http://localhost:8080/api/client_attendance/${userId}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );

        const data = await res.json();
        const list = Array.isArray(data) ? data : [];

        setAttendance(list);
        setFilteredAttendance(list);
      } catch (err) {
        console.error("Error fetching attendance history:", err);
        setAttendance([]);
        setFilteredAttendance([]);
      } finally {
        setLoading(false);
      }
    };

    if (token && userId) fetchAttendance();
  }, [token, userId]);

  // 🔍 SEARCH by date & time
  useEffect(() => {
    const q = search.toLowerCase();

    setFilteredAttendance(
      attendance.filter((entry) =>
        formatDateTime(entry.date_time).toLowerCase().includes(q)
      )
    );
  }, [search, attendance]);

  return (
    <DashboardLayout menuItems={CLIENT_MENU}>
      <div className="p-6">
        <h1 className="text-3xl font-bold mb-6">Attendance History</h1>

        {/* SEARCH BAR (only addition) */}
        <input
          type="text"
          placeholder="Search by date or time..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          className="mb-4 block w-full md:w-1/3 px-3 py-2 border border-border rounded bg-input"
        />

        {loading ? (
          <p className="text-muted-foreground">Loading...</p>
        ) : filteredAttendance.length === 0 ? (
          <p className="text-muted-foreground">No attendance records found.</p>
        ) : (
          <div className="rounded-lg shadow bg-card overflow-hidden">
            <table className="w-full border-collapse">
              <thead className="bg-muted">
                <tr>
                  <th className="p-3 text-left">Date & Time</th>
                </tr>
              </thead>

              <tbody>
                {filteredAttendance
                  .sort((a, b) => new Date(b.date_time) - new Date(a.date_time))
                  .map((entry, index) => (
                    <tr
                      key={index}
                      className="border-b border-border hover:bg-muted/60 transition"
                    >
                      <td className="p-3">
                        {formatDateTime(entry.date_time)}
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
