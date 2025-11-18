export default function DashboardCard({ title, value, icon, color }) {
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
