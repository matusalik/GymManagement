import DashboardLayout from '../components/DashboardLayout'
import DashboardCard from '../components/DashboardCard'
import { CLIENT_MENU } from '../constants/menuItems'

export default function ClientDashboard() {
  return (
    <DashboardLayout menuItems={CLIENT_MENU}>
      <div>
        <h1 className="text-3xl font-bold text-foreground mb-6">Welcome to Your Dashboard</h1>
        
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          <DashboardCard title="Membership Status" value="Active" icon="✅" color="primary" />
          <DashboardCard title="Classes This Month" value="12" icon="📅" color="accent" />
          <DashboardCard title="Weight Progress" value="-5 lbs" icon="⬇️" color="primary" />
          <DashboardCard title="Streak" value="8 days" icon="🔥" color="accent" />
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
          <div className="bg-card rounded-lg shadow p-6">
            <h2 className="text-xl font-bold text-foreground mb-4">Upcoming Classes</h2>
            <div className="space-y-3">
              <div className="p-3 bg-muted rounded border-l-4 border-primary">
                <p className="font-medium">Yoga - Tomorrow 6:00 PM</p>
                <p className="text-sm text-muted-foreground">Trainer: Sarah</p>
              </div>
              <div className="p-3 bg-muted rounded border-l-4 border-accent">
                <p className="font-medium">Strength Training - Friday 9:00 AM</p>
                <p className="text-sm text-muted-foreground">Trainer: John</p>
              </div>
            </div>
          </div>

          <div className="bg-card rounded-lg shadow p-6">
            <h2 className="text-xl font-bold text-foreground mb-4">Quick Actions</h2>
            <div className="space-y-3">
              <button className="w-full bg-primary text-primary-foreground py-2 rounded-lg hover:opacity-90 transition-opacity">
                Book a Class
              </button>
              <button className="w-full bg-accent text-accent-foreground py-2 rounded-lg hover:opacity-90 transition-opacity">
                View My Progress
              </button>
              <button className="w-full bg-primary text-primary-foreground py-2 rounded-lg hover:opacity-90 transition-opacity">
                Message Trainer
              </button>
            </div>
          </div>
        </div>
      </div>
    </DashboardLayout>
  )
}
