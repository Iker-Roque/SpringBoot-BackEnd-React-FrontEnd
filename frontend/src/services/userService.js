const API_URL = 'http://localhost:8080/api/usuarios';

export const getAllUsers = async () => {
  try {
    const response = await fetch(API_URL);
    if (!response.ok) {
      throw new Error('Error al obtener usuarios');
    }
    return await response.json();
  } catch (error) {
    console.error('Error en getAllUsers:', error);
    throw error;
  }
};

export const deleteUser = async (userId) => {
  try {
    const response = await fetch(`${API_URL}/${userId}`, {
      method: 'DELETE'
    });
    if (!response.ok) {
      throw new Error('Error al eliminar usuario');
    }
    return true;
  } catch (error) {
    console.error('Error en deleteUser:', error);
    throw error;
  }
};

export const updateUser = async (userId, userData) => {
  try {
    const response = await fetch(`${API_URL}/${userId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(userData)
    });
    if (!response.ok) {
      throw new Error('Error al actualizar usuario');
    }
    return await response.json();
  } catch (error) {
    console.error('Error en updateUser:', error);
    throw error;
  }
};