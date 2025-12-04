import React, { useEffect, useState } from "react";
import DashboardLayout from "../components/DashboardLayout";
import { CLIENT_MENU } from "../constants/menuItems";
import { useAuth } from "../context/AuthContext";

export default function ClientAttendancePage() {
  const { token, userId } = useAuth();
  const [attendance, setAttendance] = useState([]);
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
        setAttendance(Array.isArray(data) ? data : []);
      } catch (err) {
        console.error("Error fetching attendance history:", err);
        setAttendance([]);
      } finally {
        setLoading(false);
      }
    };

    if (token && userId) fetchAttendance();
  }, [token, userId]);

  return (
    <DashboardLayout menuItems={CLIENT_MENU}>
      <div className="p-6">
        <h1 className="text-3xl font-bold mb-6">Attendance History</h1>

        {loading ? (
          <p className="text-muted-foreground">Loading...</p>
        ) : attendance.length === 0 ? (
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
                {attendance
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
