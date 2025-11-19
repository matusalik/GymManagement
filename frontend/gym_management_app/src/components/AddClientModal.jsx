import { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";

export default function AddClientModal({ isOpen, onClose, onSuccess }) {
  const { token } = useAuth();

  const [form, setForm] = useState({
    username: "",
    password: "",
    first_name: "",
    last_name: "",
    phone: "",
    email: "",
    date_of_birth: "",
    address: "",
    membership_id: "",
    training_goal_id: ""
  });

  const [memberships, setMemberships] = useState([]);
  const [trainingGoals, setTrainingGoals] = useState([]);
  const [loading, setLoading] = useState(false);

  // Fetch memberships and goals from backend
  useEffect(() => {
    if (!isOpen) return;

    setForm({
        username: "",
        password: "",
        first_name: "",
        last_name: "",
        phone: "",
        email: "",
        date_of_birth: "",
        address: "",
        membership_id: "",
        training_goal_id: ""
    });

    const fetchLists = async () => {
      try {
        const memRes = await fetch("http://localhost:8080/api/memberships", {
          headers: { Authorization: `Bearer ${token}` }
        });

        const goalRes = await fetch("http://localhost:8080/api/training_goals", {
          headers: { Authorization: `Bearer ${token}` }
        });

        if (!memRes.ok || !goalRes.ok) {
          throw new Error("Failed to fetch dropdown data");
        }

        setMemberships(await memRes.json());
        setTrainingGoals(await goalRes.json());

      } catch (err) {
        console.error("Error fetching membership/goal data:", err);
      }
    };

    fetchLists();
  }, [isOpen, token]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!form.membership_id || !form.training_goal_id) {
        alert("Please select membership and training goal.");
        return;
    }

    setLoading(true);

    const payload = {
        ...form,
        membership_id: parseInt(form.membership_id, 10),
        training_goal_id: parseInt(form.training_goal_id, 10),
        registration_date: new Date().toISOString().split("T")[0],
        status: "ACTIVE"
    };

    try {
      const response = await fetch("http://localhost:8080/api/clients", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
      });

      if (!response.ok) {
        throw new Error("Failed to create client");
      }

      onSuccess(); // Refresh parent page
      onClose();

    } catch (err) {
      console.error("Add client failed:", err);
      alert("Error creating client.");
    }

    setLoading(false);
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black/40 flex items-center justify-center z-50">
      <div className="bg-card p-8 rounded-lg shadow-lg w-full max-w-lg">
        <h2 className="text-2xl font-bold mb-4">Add New Client</h2>

        <form onSubmit={handleSubmit} className="space-y-4">

          {/* Username */}
          <input
            name="username"
            type="text"
            placeholder="Username"
            value={form.username}
            onChange={handleChange}
            required
            className="w-full p-2 border rounded"
          />

          {/* Password */}
          <input
            name="password"
            type="password"
            placeholder="Password"
            value={form.password}
            onChange={handleChange}
            required
            className="w-full p-2 border rounded"
          />

          {/* First name / Last name */}
          <div className="flex gap-4">
            <input
              name="first_name"
              type="text"
              placeholder="First name"
              value={form.first_name}
              onChange={handleChange}
              required
              className="w-full p-2 border rounded"
            />

            <input
              name="last_name"
              type="text"
              placeholder="Last name"
              value={form.last_name}
              onChange={handleChange}
              required
              className="w-full p-2 border rounded"
            />
          </div>

          {/* Phone */}
          <input
            name="phone"
            type="text"
            placeholder="Phone"
            value={form.phone}
            onChange={handleChange}
            required
            className="w-full p-2 border rounded"
          />

          {/* Email */}
          <input
            name="email"
            type="email"
            placeholder="Email"
            value={form.email}
            onChange={handleChange}
            required
            className="w-full p-2 border rounded"
          />

          {/* Date of birth */}
          <label className="block text-sm">Date of Birth</label>
          <input
            name="date_of_birth"
            type="date"
            value={form.date_of_birth}
            onChange={handleChange}
            required
            className="w-full p-2 border rounded"
          />

          {/* Address */}
          <input
            name="address"
            type="text"
            placeholder="Address"
            value={form.address}
            onChange={handleChange}
            required
            className="w-full p-2 border rounded"
          />

          {/* Membership dropdown */}
          <label className="block text-sm">Membership</label>
          <select
            name="membership_id"
            value={form.membership_id}
            onChange={handleChange}
            required
            className="w-full p-2 border rounded"
          >
            <option value="">Select membership</option>
            {memberships.map((m) => (
              <option key={m.membership_id} value={m.membership_id}>
                {m.name}
              </option>
            ))}
          </select>

          {/* Training Goal dropdown */}
          <label className="block text-sm">Training Goal</label>
          <select
            name="training_goal_id"
            value={form.training_goal_id}
            onChange={handleChange}
            required
            className="w-full p-2 border rounded"
          >
            <option value="">Select goal</option>
            {trainingGoals.map((g) => (
              <option key={g.training_goal_id} value={g.training_goal_id}>
                {g.name}
              </option>
            ))}
          </select>

          {/* Buttons */}
          <div className="flex justify-end gap-3 mt-4">
            <button
              type="button"
              className="px-4 py-2 bg-muted rounded-lg"
              onClick={onClose}
              disabled={loading}
            >
              Cancel
            </button>

            <button
              type="submit"
              className="px-4 py-2 bg-primary text-primary-foreground rounded-lg"
              disabled={loading}
            >
              {loading ? "Saving..." : "Add Client"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
