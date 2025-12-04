export const RECEPTIONIST_MENU = [
  { path: '/dashboard', label: 'Overview', icon: '📊' },
  { path: '/dashboard/clients', label: 'Clients', icon: '👥' },
  { path: '/dashboard/classes', label: 'Classes', icon: '📅' },
  {  path: "/dashboard/reviews", label: "Reviews", icon: '📈'},
]

export const TRAINER_MENU = [
  { label: "Dashboard", path: "/dashboard/trainer", icon: "📊" },
  { label: "My Classes", path: "/dashboard/trainer/classes", icon: "📅" },
  { label: "My Reviews", path: "/dashboard/trainer/reviews", icon: "⭐" },
  { label: "Training Plans", path: "/dashboard/trainer/training-plans", icon: "📘" }
];


export const CLIENT_MENU = [
  { path: '/dashboard/client', label: 'Overview', icon: '📊' },
  { path: '/dashboard/client/profile', label: 'Profile', icon: '👤' },
  { path: '/dashboard/client/attendance', label: 'Attendance History', icon: '📅' },
]
