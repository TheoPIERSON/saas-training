export function useTransactions() {
  const config = useRuntimeConfig();

  async function uploadTransactions(file: string | Blob) {
    const formData = new FormData();
    formData.append("file", file);

    const token = process.client ? localStorage.getItem("token") : null;

    const response = await fetch(`${config.public.apiBaseUrl}/transactions/upload-csv`, {
      method: "POST",
      body: formData,
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(`Erreur serveur : ${response.status} - ${errorText}`);
    }

    return await response.json();
  }

  return { uploadTransactions };
}
