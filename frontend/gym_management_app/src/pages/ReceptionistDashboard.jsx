import DashboardLayout from '../components/DashboardLayout'

const RECEPTIONIST_MENU = [
  { path: '/dashboard', label: 'Overview', icon: '📊' },
  { path: '/dashboard/members', label: 'Members', icon: '👥' },
  { path: '/dashboard/billing', label: 'Billing', icon: '💰' },
  { path: '/dashboard/classes', label: 'Classes', icon: '📅' },
]

export default function ReceptionistDashboard() {
  return (
    <DashboardLayout menuItems={RECEPTIONIST_MENU}>
      <div>
        <h1 className="text-3xl font-bold text-foreground mb-6">Receptionist Dashboard</h1>
        
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          <DashboardCard title="Total Members" value="242" icon="👥" color="primary" />
          <DashboardCard title="Active Classes" value="8" icon="📅" color="accent" />
          <DashboardCard title="Revenue (Month)" value="$12,450" icon="💰" color="primary" />
          <DashboardCard title="New Sign-ups" value="15" icon="✨" color="accent" />
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
          <div className="bg-card rounded-lg shadow p-6">
            <h2 className="text-xl font-bold text-foreground mb-4">Recent Registrations</h2>
            <div className="space-y-3">
              <div className="flex justify-between items-center p-3 bg-muted rounded">
                <span>John Doe</span>
                <span className="text-sm text-muted-foreground">2 hours ago</span>
              </div>
              <div className="flex justify-between items-center p-3 bg-muted rounded">
                <span>Sarah Smith</span>
                <span className="text-sm text-muted-foreground">5 hours ago</span>
              </div>
            </div>
          </div>

          <div className="bg-card rounded-lg shadow p-6">
            <h2 className="text-xl font-bold text-foreground mb-4">Quick Actions</h2>
            <div className="space-y-3">
              <button className="w-full bg-primary text-primary-foreground py-2 rounded-lg hover:opacity-90 transition-opacity">
                + Add New Member
              </button>
              <button className="w-full bg-accent text-accent-foreground py-2 rounded-lg hover:opacity-90 transition-opacity">
                Schedule Class
              </button>
              <button className="w-full bg-primary text-primary-foreground py-2 rounded-lg hover:opacity-90 transition-opacity">
                Generate Invoice
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
