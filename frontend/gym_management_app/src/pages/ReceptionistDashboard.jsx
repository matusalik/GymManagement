import React, { useState, useEffect } from 'react';
import { useAuth } from '../context/AuthContext';
import DashboardLayout from '../components/DashboardLayout';
import DashboardCard from '../components/DashboardCard';
import { RECEPTIONIST_MENU } from '../constants/menuItems';

export default function ReceptionistDashboard() {
  const { token } = useAuth();
  const [classesCount, setClassesCount] = useState(0);
  const [clientsCount, setClientsCount] = useState(0);
  const [revenue, setRevenue] = useState(0);

  useEffect(() => {
    const fetchClassesCount = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/group_classes/count', {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        });

        if (!response.ok) {
          const errorText = await response.text();
          console.error('[v0] Classes API error response:', errorText);
          throw new Error(`Failed to fetch classes count: ${response.status}`);
        }

        const data = await response.json();
        setClassesCount(data.count || data);
      } catch (err) {
        console.error('[v0] Error fetching classes count:', err);
      }
    };

    const fetchClientsCount = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/clients/count', {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        });

        if (!response.ok) {
          const errorText = await response.text();
          console.error('[v0] Clients API error response:', errorText);
          throw new Error(`Failed to fetch clients count: ${response.status}`);
        }

        const data = await response.json();
        setClientsCount(data.count || data);
      } catch (err) {
        console.error('[v0] Error fetching clients count:', err);
      }
    };

    const fetchRevenue = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/clients/revenue', {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        });

        if (!response.ok) {
          const errorText = await response.text();
          console.error('[v0] Revenue API error response:', errorText);
          throw new Error(`Failed to fetch revenue: ${response.status}`);
        }

        const data = await response.json();
        setRevenue(data.revenue || data);
      } catch (err) {
        console.error('[v0] Error fetching revenue:', err);
      }
    };

    if (token) {
      fetchClassesCount();
      fetchClientsCount();
      fetchRevenue();
    }
  }, [token]);

  return (
    <DashboardLayout menuItems={RECEPTIONIST_MENU}>
      <div>
        <h1 className="text-3xl font-bold text-foreground mb-6">Receptionist Dashboard</h1>
        
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          <DashboardCard title="Total Clients" value={clientsCount} icon="👥" color="primary" />
          <DashboardCard title="Active Classes" value={classesCount} icon="📅" color="accent" />
          <DashboardCard title="Today's Revenue" value={`$${revenue.toFixed(2)}`} icon="💰" color="primary" />
          <DashboardCard title="Pending Registrations" value="7" icon="📋" color="accent" />
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
          <div className="bg-card rounded-lg shadow p-6">
            <h2 className="text-xl font-bold text-foreground mb-4">Recent Registrations</h2>
            <div className="space-y-3">
              <div className="p-3 bg-muted rounded border-l-4 border-primary">
                <p className="font-medium">John Smith</p>
                <p className="text-sm text-muted-foreground">Registered today</p>
              </div>
              <div className="p-3 bg-muted rounded border-l-4 border-accent">
                <p className="font-medium">Maria Garcia</p>
                <p className="text-sm text-muted-foreground">Registered yesterday</p>
              </div>
            </div>
          </div>

          <div className="bg-card rounded-lg shadow p-6">
            <h2 className="text-xl font-bold text-foreground mb-4">Quick Actions</h2>
            <div className="space-y-3">
              <button className="w-full bg-primary text-primary-foreground py-2 rounded-lg hover:opacity-90 transition-opacity">
                Register New Member
              </button>
              <button className="w-full bg-accent text-accent-foreground py-2 rounded-lg hover:opacity-90 transition-opacity">
                View All Registrations
              </button>
              <button className="w-full bg-primary text-primary-foreground py-2 rounded-lg hover:opacity-90 transition-opacity">
                Generate Report
              </button>
            </div>
          </div>
        </div>
      </div>
    </DashboardLayout>
  );
}
