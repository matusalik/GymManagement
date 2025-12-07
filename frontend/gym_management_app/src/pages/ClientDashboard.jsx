import React, { useEffect, useState } from "react";
import DashboardLayout from "../components/DashboardLayout";
import { CLIENT_MENU } from "../constants/menuItems";
import { useAuth } from "../context/AuthContext";
import LeaveReviewModal from "../components/LeaveReviewModal";
import SignUpClassModal from "../components/SignUpClassModal";
import DashboardCard from "../components/DashboardCard";


export default function ClientDashboard() {
  const { token, userId } = useAuth();

  const [status, setStatus] = useState("Loading...");
  const [loadingStatus, setLoadingStatus] = useState(true);

  const [membershipName, setMembershipName] = useState("Loading...");
  const [loadingMembership, setLoadingMembership] = useState(true);

  const [totalVisits, setTotalVisits] = useState(null);
  const [loadingVisits, setLoadingVisits] = useState(true);

  const [trainingGoalName, setTrainingGoalName] = useState("Loading...");
  const [loadingTrainingGoal, setLoadingTrainingGoal] = useState(true);

  const [classes, setClasses] = useState([]);
  const [loadingClasses, setLoadingClasses] = useState(true);

  const [showReviewModal, setShowReviewModal] = useState(false);
  const [showSignUpModal, setShowSignUpModal] = useState(false);

  // 🔹 Make this available to both useEffect and onSuccess
  const fetchUpcomingClasses = async () => {
    if (!token || !userId) return;

    setLoadingClasses(true);
    try {
      const response = await fetch(
        `http://localhost:8080/api/group_classes/notdetailed_future_by_client/${userId}`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      const data = await response.json();
      setClasses(Array.isArray(data) ? data : []);
    } catch (err) {
      console.error("Error fetching upcoming classes:", err);
      setClasses([]);
    } finally {
      setLoadingClasses(false);
    }
  };

  useEffect(() => {
    if (!token || !userId) return;

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

    const fetchTrainingGoal = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/clients/training_goal_name/${userId}`,
          {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );

        const text = await response.text();
        setTrainingGoalName(text || "No training goal");
      } catch (err) {
        console.error("Error fetching training goal:", err);
        setTrainingGoalName("Unavailable");
      } finally {
        setLoadingTrainingGoal(false);
      }
    };

    // Run all
    fetchStatus();
    fetchMembership();
    fetchTotalVisits();
    fetchTrainingGoal();
    fetchUpcomingClasses();
  }, [token, userId]); // fetchUpcomingClasses uses latest token/userId

  return (
    <DashboardLayout menuItems={CLIENT_MENU}>
      <div className="p-6">
        <h1 className="text-3xl font-bold mb-6">Client Dashboard</h1>

        {/* TOP CARDS */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          <DashboardCard
            title="Status"
            value={status.toUpperCase()}
            icon="🔵"
            color="primary"
          />
          <DashboardCard
            title="Membership"
            value={membershipName}
            icon="🎟️"
            color="accent"
          />
          <DashboardCard
            title="Training Goal"
            value={trainingGoalName}
            icon="🏋️"
            color="primary"
          />
          <DashboardCard
            title="Total Visits"
            value={totalVisits}
            icon="📅"
            color="accent"
          />
        </div>


        {/* BOTTOM SECTIONS */}
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6 mt-6">

          {/* UPCOMING CLASSES */}
          <div className="bg-card rounded-lg shadow p-6">
            <h2 className="text-xl font-bold text-foreground mb-4">
              Upcoming Classes
            </h2>

            {loadingClasses ? (
              <p className="text-muted-foreground">Loading...</p>
            ) : classes.length === 0 ? (
              <p className="text-muted-foreground">No upcoming classes.</p>
            ) : (
              <div className="space-y-3 max-h-96 overflow-y-auto">
                {classes.map((cls, i) => (
                  <div
                    key={i}
                    className="p-3 bg-muted rounded border-l-4 border-accent"
                  >
                    <div className="flex justify-between items-start mb-1">
                      <p className="font-medium">{cls.name}</p>
                      <span className="text-sm text-muted-foreground">
                        {cls.clients.length}/{cls.max_participants}
                      </span>
                    </div>

                    <p className="text-sm text-muted-foreground mb-1">
                      Trainer: {cls.trainer_name} {cls.trainer_surname}
                    </p>

                    <p className="text-sm">{cls.description}</p>

                    <p className="text-xs text-muted-foreground mt-1">
                      {new Date(cls.date_time).toLocaleString()}
                    </p>
                  </div>
                ))}
              </div>
            )}
          </div>

          {/* QUICK ACTIONS */}
          <div className="bg-card rounded-lg shadow p-6">
            <h2 className="text-xl font-bold text-foreground mb-4">
              Quick Actions
            </h2>

            <div className="space-y-3">
              <button
                onClick={() => setShowSignUpModal(true)}
                className="w-full bg-primary text-primary-foreground py-2 rounded-lg hover:opacity-90 transition-opacity"
              >
                Sign Up For Group Class
              </button>

              <button
                onClick={() => setShowReviewModal(true)}
                className="w-full bg-accent text-accent-foreground py-2 rounded-lg hover:opacity-90 transition-opacity"
              >
                Leave a Review
              </button>
            </div>
          </div>
        </div>
      </div>

      {/* MODALS */}
      <LeaveReviewModal
        isOpen={showReviewModal}
        onClose={() => setShowReviewModal(false)}
        onSuccess={() => {}}
      />

      <SignUpClassModal
        isOpen={showSignUpModal}
        onClose={() => setShowSignUpModal(false)}
        onSuccess={fetchUpcomingClasses}   
      />
    </DashboardLayout>
  );
}

function Card({ title, loading, value }) {
  return (
    <div className="bg-card p-5 rounded-xl shadow border border-border">
      <h2 className="text-lg font-semibold mb-2">{title}</h2>
      {loading ? (
        <p className="text-muted-foreground">Loading...</p>
      ) : (
        <p className="text-xl font-bold text-primary">{value}</p>
      )}
    </div>
  );
}
