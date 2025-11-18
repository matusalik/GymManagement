import LoginForm from '../components/LoginForm'

export default function LoginPage() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-background to-muted flex items-center justify-center p-4">
      <div className="w-full max-w-md">
        <div className="mb-8 text-center">
          <h1 className="text-4xl font-bold text-primary">GymFlow</h1>
          <p className="mt-2 text-muted-foreground">Manage your fitness facility with ease</p>
        </div>
        <LoginForm />
      </div>
    </div>
  )
}
