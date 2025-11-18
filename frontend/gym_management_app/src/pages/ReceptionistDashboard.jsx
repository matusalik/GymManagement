import React, { useState, useEffect } from 'react';
import { useAuth } from '../context/AuthContext';
import DashboardLayout from '../components/DashboardLayout';
import DashboardCard from '../components/DashboardCard';
import { RECEPTIONIST_MENU } from '../constants/menuItems';

export default function ReceptionistDashboard() {
  const { token } = useAuth();
  const [classesCount, setClassesCount] = useState(0);
  const [clientsCount, setClientsCount] = useState(0);
  const [trainersCount, setTrainersCount] = useState(0);
  const [revenue, setRevenue] = useState(0);
  const [reviews, setReviews] = useState([]);
  const [reviewsLoading, setReviewsLoading] = useState(false);

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

    const fetchTrainersCount = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/trainers/count', {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        });

        if (!response.ok) {
          const errorText = await response.text();
          console.error('[v0] Trainers API error response:', errorText);
          throw new Error(`Failed to fetch trainers count: ${response.status}`);
        }

        const data = await response.json();
        setTrainersCount(data.count || data);
      } catch (err) {
        console.error('[v0] Error fetching trainers count:', err);
      }
    };

    const fetchRecentReviews = async () => {
      setReviewsLoading(true);
      try {
        const response = await fetch('http://localhost:8080/api/reviews/recent', {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        });

        if (!response.ok) {
          const errorText = await response.text();
          console.error('[v0] Reviews API error response:', errorText);
          throw new Error(`Failed to fetch reviews: ${response.status}`);
        }

        const data = await response.json();
        setReviews(Array.isArray(data) ? data : []);
      } catch (err) {
        console.error('[v0] Error fetching reviews:', err);
        setReviews([]);
      } finally {
        setReviewsLoading(false);
      }
    };

    if (token) {
      fetchClassesCount();
      fetchClientsCount();
      fetchTrainersCount();
      fetchRevenue();
      fetchRecentReviews();
    }
  }, [token]);

  const renderStars = (rating) => {
    return '⭐'.repeat(rating);
  };

  return (
    <DashboardLayout menuItems={RECEPTIONIST_MENU}>
      <div>
        <h1 className="text-3xl font-bold text-foreground mb-6">Receptionist Dashboard</h1>
        
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          <DashboardCard title="Total Clients" value={clientsCount} icon="👥" color="primary" />
          <DashboardCard title="Total Trainers" value={trainersCount} icon="🏋️" color="primary" />
          <DashboardCard title="Active Classes" value={classesCount} icon="📅" color="accent" />
          <DashboardCard title="Monthly Revenue" value={`$${revenue.toFixed(2)}`} icon="💰" color="accent" />
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
          <div className="bg-card rounded-lg shadow p-6">
            <h2 className="text-xl font-bold text-foreground mb-4">Recent Reviews</h2>
            <div className="space-y-3 max-h-96 overflow-y-auto">
              {reviewsLoading ? (
                <p className="text-muted-foreground">Loading reviews...</p>
              ) : reviews.length > 0 ? (
                reviews.map((review, index) => (
                  <div key={index} className="p-3 bg-muted rounded border-l-4 border-accent">
                    <div className="flex justify-between items-start mb-1">
                      <p className="font-medium text-sm">
                        {review.client_name} {review.client_surname}
                      </p>
                      <span className="text-xs">{renderStars(review.rating || review.Rating)}</span>
                    </div>
                    <p className="text-xs text-muted-foreground mb-1">
                      Trainer: {review.trainer_name} {review.trainer_surname}
                    </p>
                    <p className="text-sm">{review.comment}</p>
                    <p className="text-xs text-muted-foreground mt-1">{review.date}</p>
                  </div>
                ))
              ) : (
                <p className="text-muted-foreground">No reviews available</p>
              )}
            </div>
          </div>

          <div className="bg-card rounded-lg shadow p-6">
            <h2 className="text-xl font-bold text-foreground mb-4">Quick Actions</h2>
            <div className="space-y-3">
              <button className="w-full bg-primary text-primary-foreground py-2 rounded-lg hover:opacity-90 transition-opacity">
                Register New Member
              </button>
              <button className="w-full bg-accent text-accent-foreground py-2 rounded-lg hover:opacity-90 transition-opacity">
                View All Reviews
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