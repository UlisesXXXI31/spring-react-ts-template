import { useEffect, useState } from "react";

// 1. Definimos una interfaz genérica de ejemplo (la cambiarás en cada nuevo proyecto)
interface DataItem {
  id: number;
  name: string;
  status: string;
}

function App() {
  const [items, setItems] = useState<DataItem[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    console.log("🚀 React: Iniciando petición al backend...");
    
    // 2. Buena práctica: Lee la URL desde variables de entorno de Vite.
    // Si no existe, usa localhost por defecto para desarrollo.
    const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';

    fetch(`${API_URL}/items`) // Reemplazar '/items' por tu endpoint real en el futuro
      .then((response) => {
        console.log("📡 React: Estado de la respuesta HTTP:", response.status);
        if (!response.ok) {
          throw new Error(`Error en el servidor: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        console.log("📦 React: Datos JSON recibidos con éxito:", data);
        setItems(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error conectando con el backend:", error);
        setLoading(false);
      });
  }, []);

  return (
    <div style={{ padding: '40px', fontFamily: 'sans-serif', maxWidth: '800px', margin: '0 auto' }}>
      
      {/* Encabezado Genérico */}
      <header style={{ borderBottom: '2px solid #3f51b5', paddingBottom: '10px', marginBottom: '30px' }}>
        <h1 style={{ color: 'var(--accent)', margin: 0 }}>🚀 Mi Nueva Aplicación</h1>
        <p style={{ color: '#666', marginTop: '5px' }}>Template Base: React (TS) + Spring Boot</p>
      </header>

      {loading ? (
        <p>Conectando con el servidor...</p>
      ) : (
        <div>
          <h2>Registros Detectados ({items.length})</h2>
          
          {items.length === 0 ? (
            <div style={{ backgroundColor: '#f5f5f5', padding: '20px', borderRadius: '8px', textAlign: 'center' }}>
              <p style={{ color: '#555', fontWeight: 'bold' }}>El ecosistema base está funcionando.</p>
              <p style={{ fontSize: '14px', color: '#888' }}>
                La conexión con el backend fue exitosa, pero no hay datos que mostrar aún.
              </p>
            </div>
          ) : (
            <ul style={{ listStyleType: 'none', padding: 0 }}>
              {items.map((item) => (
                <li key={item.id} style={{ 
                  backgroundColor: '#f9f9f9', 
                  margin: '10px 0', 
                  padding: '15px', 
                  borderRadius: '6px',
                  borderLeft: '5px solid #3f51b5'
                }}>
                  <strong>{item.name}</strong> — Estado: {item.status}
                </li>
              ))}
            </ul>
          )}
        </div>
      )}
    </div>
  );
}

export default App;