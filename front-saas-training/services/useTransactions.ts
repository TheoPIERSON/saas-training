export function useTransactions() {
  const config = useRuntimeConfig();

  async function uploadTransactions(file: string | Blob) {
    const formData = new FormData();
    formData.append("file", file);

    const token = process.client ? localStorage.getItem("token") : null;

    // Ne pas définir le Content-Type, laissez le navigateur le faire automatiquement
    const response = await fetch(`${config.public.apiBaseUrl}/api/transactions/upload-csv`, {
      method: "POST",
      body: formData,
      headers: token ? { Authorization: `Bearer ${token}` } : {},
      // Important: ne pas définir Content-Type
    });

    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(`Erreur serveur : ${response.status} - ${errorText}`);
    }

    return await response.json();
    console.log("Token utilisé :", token);
  }

  return { uploadTransactions };
}
