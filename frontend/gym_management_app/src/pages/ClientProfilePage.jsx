import React, { useEffect, useState } from "react";
import DashboardLayout from "../components/DashboardLayout";
import { CLIENT_MENU } from "../constants/menuItems";
import { useAuth } from "../context/AuthContext";
import EditProfileModal from "../components/EditProfileModal";
import ChangePasswordModal from "../components/ChangePasswordModal";
import ChangeTrainingGoalModal from "../components/ChangeTrainingGoalModal";




export default function ClientProfilePage() {
  const { token, userId } = useAuth();
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [showEditModal, setShowEditModal] = useState(false);
  const [showChangePassModal, setShowChangePassModal] = useState(false);
  const [trainingGoalName, setTrainingGoalName] = useState("Loading...");
  const [loadingTrainingGoal, setLoadingTrainingGoal] = useState(true);
  const [showTrainingGoalModal, setShowTrainingGoalModal] = useState(false);





  useEffect(() => {
    const fetchUser = async () => {
      try {
        const res = await fetch(
          `http://localhost:8080/api/users/notdetailed/${userId}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json"
            }
          }
        );

        const data = await res.json();
        setUser(data);
      } catch (err) {
        console.error("Error fetching user:", err);
        setUser(null);
      } finally {
        setLoading(false);
      }
    };

    if (token && userId){
        fetchUser();
        fetchTrainingGoal();
    } 
  }, [token, userId]);
    const fetchTrainingGoal = async () => {
    try {
        const res = await fetch(
        `http://localhost:8080/api/clients/training_goal_name/${userId}`,
        {
            headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
            }
        }
        );

        const text = await res.text();
        setTrainingGoalName(text || "No training goal");
    } catch (err) {
        console.error("Error fetching training goal:", err);
        setTrainingGoalName("Unavailable");
    } finally {
        setLoadingTrainingGoal(false);
    }
    };

  return (
  <DashboardLayout menuItems={CLIENT_MENU}>
    <div className="p-6">
      <h1 className="text-3xl font-bold text-foreground mb-6">My Profile</h1>

      {loading ? (
        <p className="text-muted-foreground">Loading...</p>
      ) : !user ? (
        <p className="text-red-500">Failed to load profile.</p>
      ) : (
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">

          {/* LEFT SIDE — Profile Info */}
          <div className="bg-card p-6 rounded-xl shadow border border-border">
            <h2 className="text-2xl font-bold mb-4">Profile Information</h2>

            <div className="mb-4">
              <h3 className="text-sm font-semibold text-primary">Username</h3>
              <p className="text-foreground">{user.username}</p>
            </div>

            <div className="mb-4">
              <h3 className="text-sm font-semibold text-primary">Full Name</h3>
              <p className="text-foreground">
                {user.first_name} {user.last_name}
              </p>
            </div>

            <div className="mb-4">
              <h3 className="text-sm font-semibold text-primary">Phone Number</h3>
              <p className="text-foreground">{user.phone}</p>
            </div>

            <div className="mb-4">
              <h3 className="text-sm font-semibold text-primary">Email</h3>
              <p className="text-foreground">{user.email}</p>
            </div>
            <div className="mb-4">
            <h3 className="text-sm font-semibold text-primary">Training Goal</h3>

            {loadingTrainingGoal ? (
                <p className="text-muted-foreground">Loading...</p>
            ) : (
                <p className="text-foreground">{trainingGoalName}</p>
            )}
            </div>
          </div>

          {/* RIGHT SIDE — Quick Actions */}
          <div className="bg-card p-6 rounded-xl shadow border border-border">
            <h2 className="text-2xl font-bold mb-4">Quick Actions</h2>

            <div className="space-y-3">

              <button
                onClick={() => setShowEditModal(true)}
                className="w-full bg-primary text-primary-foreground py-2 rounded-lg hover:opacity-90 transition"
                >
                Edit Profile
                </button>

              <button
                onClick={() => setShowChangePassModal(true)}
                className="w-full bg-primary text-primary-foreground py-2 rounded-lg hover:opacity-90 transition"
                >
                Change Password
                </button>


              <button
                onClick={() => setShowTrainingGoalModal(true)}
                className="w-full bg-primary text-primary-foreground py-2 rounded-lg hover:opacity-90 transition"
              >
                Change Training Goal
              </button>


            </div>
          </div>

        </div>
      )}
    </div>
    <EditProfileModal
    isOpen={showEditModal}
    onClose={() => setShowEditModal(false)}
    userData={user}
    onSuccess={() => window.location.reload()}   
    />
    <ChangePasswordModal
    isOpen={showChangePassModal}
    onClose={() => setShowChangePassModal(false)}
    />
    <ChangeTrainingGoalModal
      isOpen={showTrainingGoalModal}
      onClose={() => setShowTrainingGoalModal(false)}
      onSuccess={() => window.location.reload()} 
    />

  </DashboardLayout>
);
}
