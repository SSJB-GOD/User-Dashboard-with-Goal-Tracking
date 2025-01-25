import React, { useState } from "react"

function UserList({ users, onUserSelect }) {
  const [searchTerm, setSearchTerm] = useState("")
  const [sortBy, setSortBy] = useState("name")

  const filteredUsers = users.filter(
    (user) =>
      user.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
      user.email.toLowerCase().includes(searchTerm.toLowerCase()),
  )

  const sortedUsers = [...filteredUsers].sort((a, b) => a[sortBy].localeCompare(b[sortBy]))

  return (
    <div className="user-list">
      <h2>User List</h2>
      <input
        type="text"
        placeholder="Search users..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        className="search-input"
      />
      <select value={sortBy} onChange={(e) => setSortBy(e.target.value)} className="sort-select">
        <option value="name">Sort by Name</option>
        <option value="email">Sort by Email</option>
      </select>
      <ul>
        {sortedUsers.map((user) => (
          <li key={user.id} onClick={() => onUserSelect(user)} className="user-item">
            <span>{user.name}</span>
            <span>{user.email}</span>
            <span>Goals: {user.goals.length}</span>
          </li>
        ))}
      </ul>
    </div>
  )
}

export default UserList

