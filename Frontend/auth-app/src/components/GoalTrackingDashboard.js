import React from "react"

function GoalTrackingDashboard({ users }) {
  const totalGoals = users.reduce((sum, user) => sum + user.goals.length, 0)
  const completedGoals = users.reduce(
    (sum, user) => sum + user.goals.filter((goal) => goal.status === "Completed").length,
    0,
  )
  const completionPercentage = totalGoals > 0 ? Math.round((completedGoals / totalGoals) * 100) : 0

  return (
    <div className="goal-tracking-dashboard">
      <h2>Goal Tracking Dashboard</h2>
      <div className="dashboard-stats">
        <div className="stat-item">
          <span>Total Goals:</span>
          <span>{totalGoals}</span>
        </div>
        <div className="stat-item">
          <span>Completed Goals:</span>
          <span>{completedGoals}</span>
        </div>
        <div className="stat-item">
          <span>Completion Percentage:</span>
          <span>{completionPercentage}%</span>
        </div>
      </div>
    </div>
  )
}

export default GoalTrackingDashboard

