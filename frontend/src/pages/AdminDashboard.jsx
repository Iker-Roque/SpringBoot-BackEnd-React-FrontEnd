// src/pages/AdminDashboard.jsx
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Shield, Users, Package, BarChart3, Settings } from 'lucide-react';
import UsersTable from '../components/UsersTable';

const AdminDashboard = () => {
  const [user, setUser] = useState(null);
  const [currentView, setCurrentView] = useState('dashboard'); // dashboard, users, products, etc.
  const navigate = useNavigate();

  useEffect(() => {
    // Verificar si hay un usuario logueado
    const storedUser = localStorage.getItem('user');
    if (!storedUser) {
      navigate('/');
      return;
    }

    const userData = JSON.parse(storedUser);
    
    // Verificar si es admin
    if (userData.tipo !== 'admin') {
      navigate('/');
      return;
    }

    setUser(userData);
  }, [navigate]);

  if (!user) {
    return <div>Cargando...</div>;
  }

  // Si estamos en la vista de usuarios, mostrar la tabla
  if (currentView === 'users') {
    return <UsersTable onBack={() => setCurrentView('dashboard')} />;
  }

  const adminMenuItems = [
    { icon: Users, label: 'Usuarios', count: '1,234' },
    { icon: Package, label: 'Productos', count: '456' },
    { icon: BarChart3, label: 'Ventas', count: '$12,345' },
    { icon: Settings, label: 'Configuración', count: '8' }
  ];

  return (
    <div className="min-h-screen bg-gray-50">
      <div className="max-w-7xl mx-auto px-4 py-8">
        {/* Header */}
        <div className="mb-8">
          <div className="flex items-center gap-3 mb-2">
            <Shield className="text-red-600 w-8 h-8" />
            <h1 className="text-3xl font-bold text-gray-900">Panel de Administración</h1>
          </div>
          <p className="text-gray-600">Bienvenido de vuelta, {user.nombre}</p>
        </div>

        {/* Stats Cards */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          {adminMenuItems.map((item, index) => (
            <div key={index} className="bg-white rounded-lg shadow-sm p-6 hover:shadow-md transition-shadow cursor-pointer">
              <div className="flex items-center justify-between">
                <div>
                  <p className="text-sm font-medium text-gray-600">{item.label}</p>
                  <p className="text-2xl font-bold text-gray-900">{item.count}</p>
                </div>
                <item.icon className="w-8 h-8 text-red-600" />
              </div>
            </div>
          ))}
        </div>

        {/* Main Content */}
        <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
          {/* Recent Activity */}
          <div className="lg:col-span-2 bg-white rounded-lg shadow-sm p-6">
            <h2 className="text-xl font-semibold mb-4">Actividad Reciente</h2>
            <div className="space-y-4">
              <div className="flex items-center gap-3 p-3 bg-gray-50 rounded-lg">
                <Users className="w-5 h-5 text-blue-600" />
                <div>
                  <p className="font-medium">Nuevo usuario registrado</p>
                  <p className="text-sm text-gray-600">hace 2 minutos</p>
                </div>
              </div>
              <div className="flex items-center gap-3 p-3 bg-gray-50 rounded-lg">
                <Package className="w-5 h-5 text-green-600" />
                <div>
                  <p className="font-medium">Producto agregado</p>
                  <p className="text-sm text-gray-600">hace 15 minutos</p>
                </div>
              </div>
              <div className="flex items-center gap-3 p-3 bg-gray-50 rounded-lg">
                <BarChart3 className="w-5 h-5 text-purple-600" />
                <div>
                  <p className="font-medium">Nueva venta procesada</p>
                  <p className="text-sm text-gray-600">hace 1 hora</p>
                </div>
              </div>
            </div>
          </div>

          {/* Quick Actions */}
          <div className="bg-white rounded-lg shadow-sm p-6">
            <h2 className="text-xl font-semibold mb-4">Acciones Rápidas</h2>
            <div className="space-y-3">
              <button className="w-full bg-red-600 text-white py-2 px-4 rounded-lg hover:bg-red-700 transition-colors">
                Agregar Producto
              </button>
              <button 
                onClick={() => setCurrentView('users')}
                className="w-full bg-blue-600 text-white py-2 px-4 rounded-lg hover:bg-blue-700 transition-colors"
              >
                Ver Usuarios
              </button>
              <button className="w-full bg-green-600 text-white py-2 px-4 rounded-lg hover:bg-green-700 transition-colors">
                Reportes
              </button>
              <button className="w-full bg-gray-600 text-white py-2 px-4 rounded-lg hover:bg-gray-700 transition-colors">
                Configuración
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminDashboard;