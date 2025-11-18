import DashboardLayout from '../components/DashboardLayout'

const TRAINER_MENU = [
  { path: '/dashboard', label: 'Overview', icon: '📊' },
  { path: '/dashboard/clients', label: 'My Clients', icon: '👥' },
  { path: '/dashboard/workouts', label: 'Workouts', icon: '💪' },
  { path: '/dashboard/schedule', label: 'Schedule', icon: '📅' },
]

export default function TrainerDashboard() {
  return (
    <DashboardLayout menuItems={TRAINER_MENU}>
      <div>
        <h1 className="text-3xl font-bold text-foreground mb-6">Trainer Dashboard</h1>
        
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          <DashboardCard title="Active Clients" value="18" icon="👥" color="primary" />
          <DashboardCard title="Classes Today" value="5" icon="📅" color="accent" />
          <DashboardCard title="Sessions This Week" value="12" icon="💪" color="primary" />
          <DashboardCard title="Client Retention" value="95%" icon="📈" color="accent" />
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
          <div className="bg-card rounded-lg shadow p-6">
            <h2 className="text-xl font-bold text-foreground mb-4">Today's Schedule</h2>
            <div className="space-y-3">
              <div className="p-3 bg-muted rounded border-l-4 border-primary">
                <p className="font-medium">CrossFit Class - 9:00 AM</p>
                <p className="text-sm text-muted-foreground">12 participants</p>
              </div>
              <div className="p-3 bg-muted rounded border-l-4 border-accent">
                <p className="font-medium">Personal Training - 1:00 PM</p>
                <p className="text-sm text-muted-foreground">Client: Mike Johnson</p>
              </div>
            </div>
          </div>

          <div className="bg-card rounded-lg shadow p-6">
            <h2 className="text-xl font-bold text-foreground mb-4">Quick Actions</h2>
            <div className="space-y-3">
              <button className="w-full bg-primary text-primary-foreground py-2 rounded-lg hover:opacity-90 transition-opacity">
                Create Workout Plan
              </button>
              <button className="w-full bg-accent text-accent-foreground py-2 rounded-lg hover:opacity-90 transition-opacity">
                Track Client Progress
              </button>
              <button className="w-full bg-primary text-primary-foreground py-2 rounded-lg hover:opacity-90 transition-opacity">
                Message Clients
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
