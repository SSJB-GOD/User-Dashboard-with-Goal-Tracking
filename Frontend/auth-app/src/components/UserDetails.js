import React from "react"

function UserDetails({ user, onClose }) {
  if (!user) return null

  return (
    <div className="user-details">
      <h2>{user.name}'s Goals</h2>
      <button onClick={onClose} className="close-button">
        Close
      </button>
      <ul>
        {user.goals.map((goal) => (
          <li key={goal.id} className="goal-item">
            <h3>{goal.title}</h3>
            <p>Deadline: {goal.deadline}</p>
            <p>Status: {goal.status}</p>
          </li>
        ))}
      </ul>
    </div>
  )
}

export default UserDetails

