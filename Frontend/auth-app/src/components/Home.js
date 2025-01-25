import React, { useState } from "react"
import { useNavigate } from "react-router-dom"
import UserList from "./UserList"
import AddUser from "./AddUser"
import UserDetails from "./UserDetails"
import GoalTrackingDashboard from "./GoalTrackingDashboard"
import { initialUsers } from "../data/userData"

function Home() {
  const [users, setUsers] = useState(initialUsers)
  const [selectedUser, setSelectedUser] = useState(null)
  const navigate = useNavigate()

  const handleLogout = () => {
    localStorage.removeItem("token")
    navigate("/login")
  }

  const handleAddUser = (newUser) => {
    setUsers([...users, { ...newUser, id: users.length + 1 }])
  }

  const handleUserSelect = (user) => {
    setSelectedUser(user)
  }

  const handleCloseUserDetails = () => {
    setSelectedUser(null)
  }

  return (
    <div className="home">
      <h1>User Management Dashboard</h1>
      <button onClick={handleLogout} className="logout-button">
        Logout
      </button>
      <div className="dashboard-container">
        <div className="main-content">
          <GoalTrackingDashboard users={users} />
          <UserList users={users} onUserSelect={handleUserSelect} />
          <AddUser onAddUser={handleAddUser} />
        </div>
        {selectedUser && (
          <div className="sidebar">
            <UserDetails user={selectedUser} onClose={handleCloseUserDetails} />
          </div>
        )}
      </div>
    </div>
  )
}

export default Home

