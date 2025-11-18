import DashboardLayout from '../components/DashboardLayout'

const CLIENT_MENU = [
  { path: '/dashboard', label: 'Overview', icon: '📊' },
  { path: '/dashboard/my-workouts', label: 'My Workouts', icon: '💪' },
  { path: '/dashboard/classes', label: 'Classes', icon: '📅' },
  { path: '/dashboard/progress', label: 'Progress', icon: '📈' },
]

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

function DashboardCard({ title, value, icon, color }) {
  return (
    <div className="bg-card rounded-lg shadow p-6">
      <div className="flex justify-between items-start">
        <div>
          <p className="text-muted-foreground text-sm">{title}</p>
          <p className={`text-2xl font-bold mt-2 ${color === 'primary' ? 'text-primary' : 'text-accent'}`}>
            {value}
          </p>
        </div>
        <span className="text-3xl">{icon}</span>
      </div>
    </div>
  )
}
