import React from "react";
import { useNavigate } from "react-router-dom";

export default function PageHeader({
  title,
  showSearch = false,
  searchValue = "",
  onSearchChange = () => {},
  searchPlaceholder = "Search..."
}) {
  const navigate = useNavigate();

  return (
    <div className="mb-6">
      <h1 className="text-3xl font-bold text-foreground mb-6">
        {title}
      </h1>

      <div className="flex flex-col gap-4">
        <button
          onClick={() => navigate("/dashboard")}
          className="bg-primary text-primary-foreground px-4 py-2 rounded-lg hover:opacity-90 transition w-fit"
        >
          ← Back to Dashboard
        </button>

        {showSearch && (
          <input
            type="text"
            value={searchValue}
            onChange={(e) => onSearchChange(e.target.value)}
            placeholder={searchPlaceholder}
            className="w-full md:w-1/3 px-3 py-2 border border-border rounded bg-input"
          />
        )}
      </div>
    </div>
  );
}
