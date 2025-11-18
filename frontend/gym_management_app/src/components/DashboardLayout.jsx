import Sidebar from './Sidebar'

export default function DashboardLayout({ children, menuItems }) {
  return (
    <div className="flex gap-0">
      <Sidebar menuItems={menuItems} />
      <main className="flex-1 bg-background overflow-auto">
        <div className="p-8">
          {children}
        </div>
      </main>
    </div>
  )
}
