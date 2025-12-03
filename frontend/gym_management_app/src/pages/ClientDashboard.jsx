import React, { useEffect, useState } from "react";
import DashboardLayout from "../components/DashboardLayout";
import { CLIENT_MENU } from "../constants/menuItems";
import { useAuth } from "../context/AuthContext";

export default function ClientDashboard() {
  const { token, userId } = useAuth();

  const [status, setStatus] = useState("Loading...");
  const [loadingStatus, setLoadingStatus] = useState(true);

  const [membershipName, setMembershipName] = useState("Loading...");
  const [loadingMembership, setLoadingMembership] = useState(true);

  const [totalVisits, setTotalVisits] = useState(null);
  const [loadingVisits, setLoadingVisits] = useState(true);

  useEffect(() => {
    if (!token || !userId) return;

    // Fetch STATUS
    const fetchStatus = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/clients/status/${userId}`,
          {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );

        const text = await response.text();
        setStatus(text || "Unknown");
      } catch (err) {
        console.error("Error fetching status:", err);
        setStatus("Unavailable");
      } finally {
        setLoadingStatus(false);
      }
    };

    // Fetch MEMBERSHIP NAME
    const fetchMembership = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/clients/membership_name/${userId}`,
          {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );

        const text = await response.text();
        setMembershipName(text || "No membership");
      } catch (err) {
        console.error("Error fetching membership:", err);
        setMembershipName("Unavailable");
      } finally {
        setLoadingMembership(false);
      }
    };

    // Fetch TOTAL VISITS
    const fetchTotalVisits = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/client_attendance/count/${userId}`,
          {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );

        const number = await response.json();
        setTotalVisits(number);
      } catch (err) {
        console.error("Error fetching visit count:", err);
        setTotalVisits(0);
      } finally {
        setLoadingVisits(false);
      }
    };

    // Run all API calls
    fetchStatus();
    fetchMembership();
    fetchTotalVisits();
  }, [token, userId]);

  return (
    <DashboardLayout menuItems={CLIENT_MENU}>
      <div className="p-6">
        <h1 className="text-3xl font-bold mb-6">Client Dashboard</h1>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-6">

          {/* STATUS */}
          <div className="bg-card p-5 rounded-xl shadow border border-border">
            <h2 className="text-lg font-semibold mb-2">Status</h2>
            {loadingStatus ? (
              <p className="text-muted-foreground">Loading...</p>
            ) : (
              <p className="text-xl font-bold text-primary">
                {status.toUpperCase()}
              </p>
            )}
          </div>

          {/* MEMBERSHIP */}
          <div className="bg-card p-5 rounded-xl shadow border border-border">
            <h2 className="text-lg font-semibold mb-2">Membership</h2>
            {loadingMembership ? (
              <p className="text-muted-foreground">Loading...</p>
            ) : (
              <p className="text-xl font-bold text-primary">
                {membershipName}
              </p>
            )}
          </div>

          {/* TOTAL VISITS */}
          <div className="bg-card p-5 rounded-xl shadow border border-border">
            <h2 className="text-lg font-semibold mb-2">Total Visits</h2>
            {loadingVisits ? (
              <p className="text-muted-foreground">Loading...</p>
            ) : (
              <p className="text-2xl font-bold text-primary">
                {totalVisits}
              </p>
            )}
          </div>

        </div>

      </div>
    </DashboardLayout>
  );
}
