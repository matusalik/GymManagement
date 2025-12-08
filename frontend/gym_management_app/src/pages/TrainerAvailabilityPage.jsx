import React, { useEffect, useState } from "react";
import DashboardLayout from "../components/DashboardLayout";
import { TRAINER_MENU } from "../constants/menuItems";
import { useAuth } from "../context/AuthContext";
import AddAvailabilityModal from "../components/AddAvailabilityModal";
import EditAvailabilityModal from "../components/EditAvailabilityModal";
import DeleteAvailabilityModal from "../components/DeleteAvailabilityModal";
import { useNavigate } from "react-router-dom";


const START_HOUR = 6;
const END_HOUR = 22; // 6–22 inclusive

export default function TrainerAvailabilityPage() {
  const { token, userId } = useAuth();
  const [availability, setAvailability] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showAddModal, setShowAddModal] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const navigate = useNavigate();


  const DAYS = [
    "MONDAY",
    "TUESDAY",
    "WEDNESDAY",
    "THURSDAY",
    "FRIDAY",
    "SATURDAY",
    "SUNDAY",
  ];

  const HOURS = Array.from(
    { length: END_HOUR - START_HOUR + 1 },
    (_, i) => START_HOUR + i
  );

  useEffect(() => {
    const fetchAvailability = async () => {
      try {
        const res = await fetch(
          `http://localhost:8080/api/trainer_availability/notdetailed/${userId}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );

        const data = await res.json();
        setAvailability(Array.isArray(data) ? data : []);
      } catch (err) {
        console.error("Error fetching availability:", err);
        setAvailability([]);
      } finally {
        setLoading(false);
      }
    };

    if (token && userId) {
      fetchAvailability();
    }
  }, [token, userId]);

  const timeToHours = (timeStr) => {
    // "HH:MM:SS" -> decimal hour, e.g. "13:30:00" -> 13.5
    if (!timeStr) return 0;
    const [h, m] = timeStr.split(":");
    return parseInt(h, 10) + parseInt(m, 10) / 60;
  };

  const formatTime = (timeStr) => {
    if (!timeStr) return "";
    const [h, m] = timeStr.split(":");
    return `${h}:${m}`;
  };

  const totalHours = END_HOUR - START_HOUR;

  return (
    <DashboardLayout menuItems={TRAINER_MENU}>
      <div className="p-6 grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* LEFT: Graphical Weekly Calendar */}
        <div className="lg:col-span-2">
          <h1 className="text-3xl font-bold text-foreground mb-6">
            Weekly Availability
          </h1>
          <button
            onClick={() => navigate("/dashboard")}
            className="mb-4 bg-primary text-primary-foreground px-4 py-2 rounded-lg hover:opacity-90 transition"
          >
            ← Back to Dashboard
          </button>
          {loading ? (
            <p className="text-muted-foreground">Loading...</p>
          ) : (
            <div className="overflow-x-auto">
              <div className="min-w-[900px]">
                {/* Time axis header */}
                <div className="grid grid-cols-[80px,1fr] gap-2 mb-2">
                  <div></div>
                  <div className="relative h-6">
                    <div className="absolute inset-y-0 left-0 right-0 flex justify-between text-[10px] text-muted-foreground">
                      {HOURS.map((h) => (
                        <span
                          key={h}
                          className="translate-x-[-50%]"
                        >
                          {String(h).padStart(2, "0")}:00
                        </span>
                      ))}
                    </div>
                  </div>
                </div>

                {/* Day rows */}
                {DAYS.map((day) => {
                  const slots = availability.filter(
                    (a) => a.day_of_the_week === day
                  );

                  return (
                    <div
                      key={day}
                      className="grid grid-cols-[80px,1fr] gap-2 items-center mb-2"
                    >
                      {/* Day label */}
                      <div className="text-sm font-medium text-muted-foreground">
                        {day.substring(0, 3)}
                      </div>

                      {/* Time track */}
                      <div className="relative h-10 bg-muted rounded-md overflow-hidden">
                        {/* Background hour grid */}
                        <div className="absolute inset-0 flex">
                          {HOURS.map((h, idx) => (
                            <div
                              key={h}
                              className={`flex-1 border-l border-border/40 ${
                                idx === HOURS.length - 1
                                  ? "border-r border-border/40"
                                  : ""
                              }`}
                            />
                          ))}
                        </div>

                        {/* Availability blocks */}
                        {slots.length === 0 ? (
                          <span className="relative z-10 text-xs text-muted-foreground flex items-center h-full pl-2">
                            No availability
                          </span>
                        ) : (
                          slots.map((slot, idx) => {
                            let start = timeToHours(slot.start_time);
                            let end = timeToHours(slot.end_time);

                            // clamp to 6–22
                            start = Math.max(START_HOUR, start);
                            end = Math.min(END_HOUR, end);

                            if (end <= start) return null;

                            const leftPercent = ((start - START_HOUR) / totalHours) * 100;
                            const widthPercent = Math.max(((end - start) / totalHours) * 100, 0.5);

                            return (
                              <div
                                key={idx}
                                className="absolute top-1 bottom-1 rounded-md bg-emerald-500/80 text-[10px] text-white flex items-center justify-center px-2 shadow"
                                style={{
                                  left: `${leftPercent}%`,
                                  width: `${widthPercent}%`,
                                }}
                              >
                                {formatTime(slot.start_time)} –{" "}
                                {formatTime(slot.end_time)}
                              </div>
                            );
                          })
                        )}
                      </div>
                    </div>
                  );
                })}
              </div>
            </div>
          )}
        </div>

        {/* RIGHT: Quick Actions */}
        <div className="bg-card rounded-lg shadow p-6 border border-border">
          <h2 className="text-xl font-bold mb-4">Quick Actions</h2>

          <div className="space-y-3">
            <button
              onClick={() => setShowAddModal(true)}
              className="w-full py-2 bg-primary text-primary-foreground rounded-lg hover:opacity-90 transition"
            >
              Add New Availability
            </button>
            <button
              onClick={() => setShowEditModal(true)}
              className="w-full py-2 bg-accent text-accent-foreground rounded-lg hover:opacity-90 transition"
            >
              Change Availability Hours
            </button>
            <button
              onClick={() => setShowDeleteModal(true)}
              className="w-full py-2 bg-destructive text-destructive-foreground rounded-lg hover:opacity-90 transition"
            >
              Delete Availability
            </button>

          </div>
        </div>
      </div>
      <AddAvailabilityModal
        isOpen={showAddModal}
        onClose={() => setShowAddModal(false)}
        onSuccess={() => window.location.reload()}
      />
      <EditAvailabilityModal
        isOpen={showEditModal}
        onClose={() => setShowEditModal(false)}
        onSuccess={() => window.location.reload()}
      />
      <DeleteAvailabilityModal
        isOpen={showDeleteModal}
        onClose={() => setShowDeleteModal(false)}
        onSuccess={() => window.location.reload()}
      />
    </DashboardLayout>
  );
}
